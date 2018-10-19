package com.hdac.sdk.sample;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.bitcoinj.core.Base58;
import org.bitcoinj.core.ECKey;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hdacSdk.hdacCoreApi.CommandException;
import com.hdacSdk.hdacCoreApi.CommandUtils;
import com.hdacSdk.hdacCoreApi.HdacApiManager;
import com.hdacSdk.hdacCoreApi.HdacCommand;
import com.hdacSdk.hdacCoreApi.HdacException;
import com.hdacSdk.hdacCoreApi.HdacRpcClient;
import com.hdacSdk.hdacCoreApi.RpcHandler;
import com.hdacSdk.hdacWallet.HdacCoreAddrParams;
import com.hdacSdk.hdacWallet.HdacMultiSigTransaction;
import com.hdacSdk.hdacWallet.HdacNetworkParams;
import com.hdacSdk.hdacWallet.HdacTransaction;
import com.hdacSdk.hdacWallet.HdacWallet;
import com.hdacSdk.hdacWallet.HdacWalletManager;
import com.hdacSdk.hdacWallet.HdacWalletUtils;
import com.hdacSdk.hdacWallet.HdacWalletUtils.NnmberOfWords;

public class HdacSamplePane extends JPanel implements ActionListener, RpcHandler {
	
	private static final String rpcIp = "http://172.0.0.1";
	private static final String rpcPort = "28822";
	private static final String chainName = "hdac";
	private static final String rpcUser = "hdac";
	private static final String rpcPassword = "hdac1234";
	
	private HdacWalletManager hdacWalletMgr;
	private HdacApiManager hdacMCMgr;
	private HdacWallet hdacWallet;
	private HdacRpcClient hdacRpcClient;
	private HdacCommand hdacCommand;
	private HdacCommand hdacCommandSvc;
	private JTextArea txList;
	private JTextArea resTextArea;
	
	private List<String> seedWords = null;
    private JTextArea txtSeedWords;
	
	private JTextArea txtAddress;
	private JTextArea txtBalance;
	
	private JCheckBox highFee;
    private JCheckBox mediumFee;
    private JCheckBox rawFee;
    
    private long fee;
    
    private JTextArea txtToAddress;
    private JTextArea txtSendAmount;
    
	public HdacSamplePane() {
		super();
        
		//GUI
		//>
		setLayout(null);
        setBackground(new Color(0x1c2134));        
        
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = screen.width;
        int frameHeight = screen.height;
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        
        addScreenComponents();    
        //<
        
        //SDK preparation
        //>
        try {
        	hdacWalletMgr = HdacWalletManager.getInstance(); 
        	hdacMCMgr = new HdacApiManager();   
        	
            hdacRpcClient = createHdacRpcClient(this);
            
            hdacCommand = new HdacCommand(hdacRpcClient);
            hdacCommandSvc = new HdacCommand(createHdacRpcClient( 
        		new RpcHandler() {

						@Override
						public void onError(int method, String errMsg, int ceeCode) {
							// TODO Auto-generated method stub
							System.out.print("send error : " + errMsg + "\n");
						}

						@Override
						public void onResponse(int method, JSONObject data) {
							// TODO Auto-generated method stub
							System.out.print("send response : " + data.toString() + "\n");
							switch(method) {
								case CommandUtils.LIST_ADDRESS_TRANSACTIONS:
									txList.removeAll();
									JSONArray result = data.getJSONArray("result");
									for(int i = 0; i<result.length();i++) {
										JSONObject tx = result.getJSONObject(i);
										txList.append("txid : " + tx.getString("txid") + "\n");
									}
									break;			 
							}								
						}							
					}
            ));
		} catch (CommandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //<
        
	}
	
	private String multiSigAddr = null;
	
	HdacWallet hdacWallet1 = null;
	HdacWallet hdacWallet2 = null;
	HdacWallet hdacWallet3 = null;
	
	HdacWallet toHdacWallet = null;
	HdacMultiSigTransaction hdacMultiSigTx = null;

	@Override
	public void actionPerformed(ActionEvent arg) {
		// TODO Auto-generated method stub
		
		String action = arg.getActionCommand();
        switch (action) {
            case "load_words":
            	List<String>seed_words = HdacWalletUtils.getRandomSeedWords(NnmberOfWords.MNEMONIC_12_WORDS);
            	System.out.print("create_wallet seedWords = " + seed_words.toString() + "\n");
            	txtSeedWords.setText(seed_words.toString().replace("[", "").replace("]", "").replace(",", ""));
            	
            	break;
            case "create_wallet":
            	HdacCoreAddrParams params = new HdacCoreAddrParams(false);
            	
            	if(seedWords==null) seedWords = new ArrayList<String>();
            	seedWords.clear();
        		String[] words = txtSeedWords.getText().split(" ");
        		for(int i=0;i<words.length;i++) {
        			seedWords.add(words[i]);
        		}
        		//create wallet
        		hdacWallet = HdacWalletManager.generateNewWallet(seedWords, "", params);
        		
            	System.out.print("create_wallet isValidWallet = " + hdacWallet.isValidWallet() + "\n");
            	if(hdacWallet.isValidWallet()) txtAddress.setText("Address : " + hdacWallet.getHdacAddress());
            	else {
            		JOptionPane.showMessageDialog(null,
            			    "Please, re-create words or check your network parameters.",
            			    "Invalid wallet",
            			    JOptionPane.ERROR_MESSAGE);
            		return;
            	}
				
				try {
					//get balance
					System.out.print("get balance " + "\n");
					hdacCommand.getaddressbalances(hdacWallet.getHdacAddress(), "1", "false");					
				} catch (CommandException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				
            	break;
            case "send":
            	//send raw transaction
            	System.out.print("send start" + "\n");  
            	if(hdacWallet!=null) {
            		//�깉濡쒖슫 rpc client �깮�꽦    
            		HdacRpcClient rpc_client = createHdacRpcClient(null);   		
            		try {
            			HdacCommand hdac_command = new HdacCommand(rpc_client);
            			
            			//get addresses in the wallet
            			//>
            		    List<String> addr_list = hdacWallet.getHdacWalletAddresses();
						JSONArray addrs = new JSONArray();
						for(int i=0;i<addr_list.size();i++)
							addrs.put(addr_list.get(i));
            			//<
						
            			hdac_command.setRpcHandler(new RpcHandler() {

							@Override
							public void onError(int method, String errMsg, int ceeCode) {
								// TODO Auto-generated method stub
								System.out.print("send error : " + errMsg + "\n");
							}

							@Override
							public void onResponse(int method, JSONObject data) {
								// TODO Auto-generated method stub
								System.out.print("send response : " + data.toString() + "\n");
								switch(method) {
									case CommandUtils.LIST_UNSPENT:
										try {
											//create raw transaction
											String raw_tx = getRawTransaction(hdacWallet, data);												
											//send raw transaction
											if(raw_tx!=null) hdacCommand.sendrawtransaction(raw_tx);											
										} catch (CommandException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
											JOptionPane.showMessageDialog(null,
						            			    "Raw Transaction : " + e.getMessage(),
						            			    "Invalid raw transaction",
						            			    JOptionPane.ERROR_MESSAGE);
											return;
										}
										break;									 
								}								
							}							
						});
						hdac_command.listunspent("0", "999999", addrs);
						
					} catch (CommandException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		
            	}

            	break;
            	
            case "refresh":
            	refresh();
            	break;
        }
		
	}
	
	private String sendMutiSigTxSample() {
		HdacWalletManager hmgr = HdacWalletManager.getInstance();
    	
    	hmgr.addNewWallet(HdacWalletUtils.getRandomSeedWords(NnmberOfWords.MNEMONIC_12_WORDS),
    			"", new HdacCoreAddrParams(false));
    	hmgr.addNewWallet(HdacWalletUtils.getRandomSeedWords(NnmberOfWords.MNEMONIC_12_WORDS),
    			"", new HdacCoreAddrParams(false));
    	hmgr.addNewWallet(HdacWalletUtils.getRandomSeedWords(NnmberOfWords.MNEMONIC_12_WORDS),
    			"", new HdacCoreAddrParams(false));
    	
    	toHdacWallet = HdacWalletManager.generateNewWallet(HdacWalletUtils.getRandomSeedWords(NnmberOfWords.MNEMONIC_12_WORDS),
    			"", new HdacCoreAddrParams(false));
    	
    	
    	hdacMultiSigTx = new HdacMultiSigTransaction(HdacSamplePane.rpcIp, HdacSamplePane.rpcPort,			
				HdacSamplePane.rpcUser, HdacSamplePane.rpcPassword, HdacSamplePane.chainName);
    	
    	multiSigAddr = hdacMultiSigTx.createMultiSigAddress(hmgr.getWalletList().size(),
    			hmgr.getWalletByIndex(0).getHdacPublicKey(), 
    			hmgr.getWalletByIndex(1).getHdacPublicKey(), 
    			hmgr.getWalletByIndex(2).getHdacPublicKey());
    	
    	System.out.print("createMultiSigAddress : " + multiSigAddr + "\n");
    	
    	hdacMultiSigTx.grantPermission(multiSigAddr);
    	hdacMultiSigTx.sendToMultiSigAddr(multiSigAddr, 1000);
    	
    	JSONObject toAddrWithData = new JSONObject();
    	toAddrWithData.put(toHdacWallet.getHdacAddress(), 10);    	
    	System.out.print("toAddrWithData : " + toAddrWithData + "\n");
    	
    	JSONArray data = new JSONArray();
    	data.put("1f9d46e951de00d1ff8110078c5a0950ef650482ee0e02757ca6efab228f9670");
    	//           .........
    	String rawTx = hdacMultiSigTx.createRawSendFrom(multiSigAddr, toAddrWithData, data, "sign");
    	
    	JSONArray utxo = hdacMultiSigTx.getListUnspent(multiSigAddr);
    	String signed3RawTx = null;
    	if(rawTx!=null
    			&& utxo!=null && utxo.length()>0) {
    		
    		String signed1RawTx = hdacMultiSigTx.signRawTransaction(rawTx, 
    				hmgr.getWalletByIndex(0).getHdacBase58PrivateKey(), utxo.getJSONObject(0));
    		
        	String signed2RawTx = hdacMultiSigTx.signRawTransaction(signed1RawTx, 
        			hmgr.getWalletByIndex(1).getHdacBase58PrivateKey(), utxo.getJSONObject(0));
        	
        	signed3RawTx = hdacMultiSigTx.signRawTransaction(signed2RawTx, 
        			hmgr.getWalletByIndex(2).getHdacBase58PrivateKey(), utxo.getJSONObject(0));
    	}
    	
    	System.out.print("signed raw transaction : " + signed3RawTx + "\n");
    	String rstTxid = hdacMultiSigTx.sendRawTrasnsaction(signed3RawTx);
    	System.out.print("sned raw transaction : " + rstTxid + "\n");    	
    	return rstTxid;
	}
	

	@Override
	public void done(int method) {
		// TODO Auto-generated method stub
		System.out.print("done " + method + "\n");
		
	}

	@Override
	public void onResponse(int method, JSONObject rst) {
		// TODO Auto-generated method stub	
		System.out.print("onResponse method " + method + "\n");
		switch(method) {
			case CommandUtils.GET_ADDRESS_BALANCES :
				try {
					if(!rst.isNull("error")) {
						System.out.print("RPC Error " + rst.get("error").toString() + "\n");
						break;
					}
					JSONArray result = rst.getJSONArray("result");
					JSONObject balance_info = (JSONObject) result.get(0);
					txtBalance.setText("Balance : " + balance_info.getString("qty"));	
					hdacCommandSvc.listaddresstransactions(hdacWallet.getHdacAddress(), "30", "0", "false");
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CommandException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case CommandUtils.LIST_UNSPENT:
				if(!rst.isNull("error")) {
					System.out.print("RPC Error " + rst.getString("error") + "\n");
					break;
				}
				System.out.print("LIST_UNSPENT " + rst.toString() + "\n");
				txtBalance.setText("Balance : " + getBalance(rst));
				try {
					hdacCommandSvc.listaddresstransactions(hdacWallet.getHdacAddress(), "30", "0", "false");
				} catch (CommandException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;	
				
			case CommandUtils.SEND_RAW_TRANSACTION:
				if(!rst.isNull("error")) {
					System.out.print("RPC Error " + rst.getString("error") + "\n");
					break;
				}
				System.out.print("SEND_RAW_TRANSACTION " + rst.toString() + "\n");
				//balance & tx list update after sending
				refresh();				
				break;
		}
		
		try {
			resTextArea.append("\n\n");
			resTextArea.append("=== RPC method ===\n" + CommandUtils.getMethodName(method) + "\n");
			resTextArea.append("=== RPC Result ===\n" + rst.toString().replace(",", ",\n    ") + "\n");
		} catch (HdacException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public void onError(int method,String err_msg, int rstCode) {
		// TODO Auto-generated method stub
		resTextArea.append("\n\n");
		resTextArea.append("RPC ResultCode : " + rstCode + "\n");
		resTextArea.append("=== RPC Error Message ===\n" + err_msg + "\n");
	}
	
	/**
	 * @brief create raw transaction
	 * @param wallet
	 * @param data result of listunspent 
	 * @return raw transaction hex-string
	 */
	private String getRawTransaction(HdacWallet wallet, JSONObject data) {
		
		HdacTransaction transaction = new HdacTransaction(wallet);

		JSONArray utxos;
		float balance = 0;
		try {
			utxos = data.getJSONArray("result");
        	for(int i=0; i<utxos.length();i++) {
				JSONObject utxo;
				utxo = utxos.getJSONObject(i);
				balance += utxo.getLong("amount");										
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		System.out.print("balance " + balance + "\n");
		
		//for checking balance 
		//>
		long lBalance = (long) (balance * Math.pow(10, 8));
		long send_amount = (long) (Long.parseLong(txtSendAmount.getText()) * Math.pow(10, 8));
		System.out.print("input " + txtSendAmount.getText() + "\n");
		long remain = lBalance - send_amount - fee;
		//<
		System.out.print("remain " + remain + "\n");
		
		if(remain >= 0) {
			transaction.addOutput(txtToAddress.getText(), send_amount);
			transaction.addOutput(wallet.getHdacAddress(), remain);
			try {
				for(int i=0; i<utxos.length();i++) {
					JSONObject utxo = utxos.getJSONObject(i);
					ECKey sign = wallet.getHdacSigKey(utxo.getString("address"));
					System.out.print("sign " + sign.toString() + "\n");
					if(sign!=null) transaction.addSignedInput(utxo, sign);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String raw_tx = transaction.getTxBuilder().build().toHex();
			System.out.print("raw_tx " + raw_tx + "\n");
			return raw_tx;
		}else {
			JOptionPane.showMessageDialog(null,
    			    "Raw Transaction : not enough hdac",
    			    "Invalid raw transaction",
    			    JOptionPane.ERROR_MESSAGE);
		}
		//<		
		return null;
	}
	
	/**
	 * @brief calculate balance
	 * @param wallet
	 * @param data result data of listunspent
	 * @return float balance
	 */
	private float getBalance(JSONObject data) {		
		JSONArray utxos;
		float balance = 0;
		try {
			utxos = data.getJSONArray("result");
    		for(int i=0; i<utxos.length();i++) {
				JSONObject utxo;
				utxo = utxos.getJSONObject(i);
				balance += utxo.getLong("amount");										
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print("balance " + balance + "\n");	
		
		return balance;
	}
	
	private HdacRpcClient createHdacRpcClient(RpcHandler handler) {
    	HdacRpcClient hdacRpcClient = null;
    	
    	try {
    		
    		/*
    		 * rpcIp: node server ip (from .hdac/[chainname]/hdac.conf)
    		 * rpcPort: node server rpc port (from .hdac/[chainname]/hdac.conf)
    		 * rpcUser: node 怨꾩젙 id (from .hdac/[chainname]/hdac.conf)
    		 * rpcPassword: node 怨꾩젙 password (from .hdac/[chainname]/hdac.conf)
    		 * chainName: node block chain name (from .hdac/[chainname] 
    		 */    		
			hdacRpcClient = HdacApiManager.createRPCClient(HdacSamplePane.rpcIp, HdacSamplePane.rpcPort,			
					HdacSamplePane.rpcUser, HdacSamplePane.rpcPassword, HdacSamplePane.chainName, handler);
			
		} catch (HdacException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return hdacRpcClient;
    }
	
	private void refresh() {
		if(hdacWallet!=null) {
			List<String> addr_list = hdacWallet.getHdacWalletAddresses();
			
			JSONArray addrs = new JSONArray();
			for(int i=0;i<addr_list.size();i++)
				addrs.put(addr_list.get(i));
			
	    	try {
	    		hdacCommand.listunspent("0", "999999", addrs);		            		
			} catch (CommandException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 *  Sample Application GUI
	 */
	private void addScreenComponents() {	
		JLabel header_bg = new JLabel("HyundaiPay");
		header_bg.setForeground(new Color(0x303344));
		header_bg.setBackground(new Color(0x303344));
		header_bg.setOpaque(true);
		header_bg.setBounds(new Rectangle(0, 0, 1920, 70));
		
		JLabel header = new JLabel(new ImageIcon("./res/imgs/hdac_logo.png"), JLabel.LEFT);
		header.setBounds(new Rectangle(20, 0, 1920, 70));
		
		txList = new JTextArea();
		txList.setFont(new Font("TimesRoman", Font.BOLD, 12));
		txList.setForeground(Color.BLACK);
		txList.setBackground(Color.WHITE);		      
        JScrollPane tx_scroll_pane = new JScrollPane(txList);  
        JLabel tx_headerLabel = new JLabel(new ImageIcon("./res/imgs/line_blue.png"), JLabel.LEFT);        
        tx_scroll_pane.setColumnHeaderView(tx_headerLabel);
        tx_scroll_pane.setBorder(new LineBorder(new Color(0x303344), 5));
        tx_scroll_pane.setBackground(new Color(0x303344));
        tx_scroll_pane.setBounds(new Rectangle(52, 100, 300, 315));
        
        resTextArea = new JTextArea();
        resTextArea.setFont(new Font("TimesRoman", Font.BOLD, 12));
        resTextArea.setForeground(Color.BLACK);
        resTextArea.setBackground(Color.WHITE);
        JScrollPane rpc_scroll_pane = new JScrollPane(resTextArea);
        JLabel rpc_headerLabel = new JLabel(new ImageIcon("./res/imgs/line_green.png"), JLabel.LEFT);
        rpc_scroll_pane.setColumnHeaderView(rpc_headerLabel);
        rpc_scroll_pane.setBorder(new LineBorder(new Color(0x303344), 5));
        rpc_scroll_pane.setBounds(new Rectangle(52, 425, 300, 315));
        
		JPanel labels = new JPanel(new GridLayout(0, 1, 1, 1));
        labels.setAlignmentX(CENTER_ALIGNMENT);
        labels.setAlignmentY(TOP_ALIGNMENT);
        labels.setBackground(new Color(0x1c2134));
        
        JLabel seed_headerLabel = new JLabel(new ImageIcon("./res/imgs/line_yellow.png"), JLabel.LEFT); 
        labels.setBorder(new LineBorder(new Color(0x303344), 1));
        txtSeedWords = new JTextArea("");
        txtSeedWords.setLineWrap(true);
        txtSeedWords.setEditable(true);
        txtSeedWords.setFont(new Font("Verdana", Font.BOLD, 17));
                
        JPanel new_buttons = new JPanel(new GridLayout(1, 0, 1, 1));
        new_buttons.setForeground(Color.DARK_GRAY);
        
        Button load_button = new Button("Load 12 Words");
        load_button.setBackground(new Color(0x1c2134));
        load_button.setForeground(Color.WHITE);
        load_button.setActionCommand("load_words");
        load_button.addActionListener(this);
        
        Button create_button = new Button("Create Wallet");
        create_button.setBackground(new Color(0x1c2134));
        create_button.setForeground(Color.WHITE);        
        create_button.setActionCommand("create_wallet");
        create_button.addActionListener(this);        
        new_buttons.add(load_button);
        new_buttons.add(create_button);
        
        labels.add(seed_headerLabel);
        labels.add(txtSeedWords);
        labels.add(new_buttons);
        labels.setBounds(new Rectangle(370, 98, 1300, 170));
        
        JPanel wallet_info = new JPanel(new GridLayout(0, 1));
        JLabel info_headerLabel = new JLabel(new ImageIcon("./res/imgs/line_gray.png"), JLabel.LEFT);         
        wallet_info.setBorder(new LineBorder(new Color(0x303344), 3));
        wallet_info.setBackground(new Color(0x303344));
        txtAddress = new JTextArea("Address : ");
        txtAddress.setEditable(false);
        txtAddress.setLineWrap(true);
        txtBalance = new JTextArea("Balance : ");
        txtBalance.setEditable(false); 
        txtBalance.setLineWrap(true);
        wallet_info.add(info_headerLabel);
        wallet_info.add(txtAddress);
        wallet_info.add(txtBalance);
        wallet_info.setBounds(new Rectangle(367, 280, 1300, 135));        
        
        JPanel transaction = new JPanel(new GridLayout(0, 1));  
        JLabel txctl_headerLabel = new JLabel(new ImageIcon("./res/imgs/line_red.png")); 
        transaction.setBorder(new LineBorder(new Color(0x303344), 1));
        transaction.setBackground(new Color(0x1c2134));
           
        JPanel tx_address_area = new JPanel(new GridLayout(4, 0, 1, 1));
        tx_address_area.setBorder(new LineBorder(new Color(0x303344), 3));
        tx_address_area.setLayout(new BoxLayout(tx_address_area, BoxLayout.X_AXIS));
        txtToAddress = new JTextArea("");
        tx_address_area.add(txctl_headerLabel);
        tx_address_area.add(txtToAddress);
        
        JTextField amount_title = new JTextField("Amount");
        amount_title.setBorder(new LineBorder(new Color(0x303344), 5));
        amount_title.setFont(new Font("ArialMT", Font.PLAIN, 18));
        amount_title.setForeground(Color.WHITE);
        amount_title.setBackground(new Color(0x303344));
        
        JPanel tx_amount_area = new JPanel(new GridLayout(1, 0, 1, 1));
        tx_amount_area.setBorder(new LineBorder(new Color(0x303344), 3));
        tx_amount_area.setLayout(new BoxLayout(tx_amount_area, BoxLayout.Y_AXIS));
        txtSendAmount = new JTextArea("");        
        txtSendAmount.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            	char c = e.getKeyChar();
				if(!(c >= 0x30 && c <= 0x39)&&!(c == 0x2E && !txtSendAmount.getText().contains(".")))
				    e.consume();	
            }

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
        });

        tx_amount_area.add(txtSendAmount);
        
        JTextField fee_title = new JTextField("Fee");
        fee_title.setBorder(new LineBorder(new Color(0x303344), 5));
        fee_title.setFont(new Font("ArialMT", Font.PLAIN, 18));
        fee_title.setForeground(Color.WHITE);
        fee_title.setBackground(new Color(0x303344));
        
        JPanel tx_fee_area = new JPanel(new GridLayout(1, 0, 1, 1)); 
        tx_fee_area.setBackground(new Color(0x303344));
        highFee = new JCheckBox("0.1 dac");
        highFee.addActionListener(e ->{
        	mediumFee.setSelected(false);
        	rawFee.setSelected(false);
        	fee = (long) (0.1 * Math.pow(10, 8));
        });
        mediumFee = new JCheckBox("0.03 dac");
        mediumFee.addActionListener(e ->{
        	highFee.setSelected(false);
        	rawFee.setSelected(false);
        	fee = (long) (0.03 * Math.pow(10, 8));
        });
        rawFee = new JCheckBox("0.01 dac");
        rawFee.addActionListener(e ->{
        	highFee.setSelected(false);
        	mediumFee.setSelected(false);
        	fee = (long) (0.01 * Math.pow(10, 8));
        });

        highFee.setBackground(new Color(0x303344));
        highFee.setForeground(Color.WHITE);
        mediumFee.setBackground(new Color(0x303344));
        mediumFee.setForeground(Color.WHITE);
        rawFee.setBackground(new Color(0x303344));        
        rawFee.setForeground(Color.WHITE);
        
        tx_fee_area.add(highFee);
        tx_fee_area.add(mediumFee);
        tx_fee_area.add(rawFee);
        
        JPanel tx_buttons = new JPanel(new GridLayout(1, 0, 1, 1));
        tx_buttons.setBackground(Color.WHITE);
        Button send_button = new Button("Send");
        send_button.setBackground(new Color(0x1c2134));
        send_button.setForeground(Color.WHITE);
        send_button.setActionCommand("send");
        send_button.addActionListener(this);        
        
        Button refresh_button = new Button("Refresh");
        refresh_button.setBackground(new Color(0x1c2134));
        refresh_button.setForeground(Color.WHITE);
        refresh_button.setActionCommand("refresh");
        refresh_button.addActionListener(this);        
        tx_buttons.add(send_button);
        tx_buttons.add(refresh_button);
        
        transaction.add(txctl_headerLabel); 
        transaction.add(tx_address_area);
        transaction.add(amount_title);
        transaction.add(tx_amount_area); 
        transaction.add(fee_title);
        transaction.add(tx_fee_area); 
        transaction.add(tx_buttons); 
        
        transaction.setBounds(new Rectangle(370, 424, 1300, 315));
        
        JPanel hdaclabels = new JPanel(new GridLayout(1, 0));
        hdaclabels.setAlignmentX(CENTER_ALIGNMENT);
        hdaclabels.setAlignmentY(TOP_ALIGNMENT);
        hdaclabels.setBackground(new Color(0x303344));
        hdaclabels.setBorder(new LineBorder(new Color(0x303344), 20));
        
        JLabel hdpay_headerLabel = new JLabel(new ImageIcon("./res/imgs/hdac_logo.png"), JLabel.RIGHT); 
        JTextArea hdpay_info = new JTextArea("");
        hdpay_info.setLineWrap(true);
        hdpay_info.setEditable(false);
        hdpay_info.setFont(new Font("ArialMT", Font.PLAIN, 15));
        hdpay_info.setText("  Hdac Technology\n  copyright 짤 2018 Hdac Technology AG. All Rights Reserved.");
        hdpay_info.setBackground(new Color(0x303344));
        hdpay_info.setForeground(Color.WHITE);
                
        hdaclabels.add(hdpay_headerLabel);
        hdaclabels.add(hdpay_info);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = screen.width;
        hdaclabels.setBounds(new Rectangle(0, 820, frameWidth, 90));

        add(header);
        add(header_bg);
        add(tx_scroll_pane);
        add(rpc_scroll_pane);
        add(labels);
        add(wallet_info);
        add(transaction);
        add(hdaclabels);
        initValues();
        
	}
		
	private void initValues() {
		highFee.setSelected(false);
		mediumFee.setSelected(false);
    	rawFee.setSelected(true);
    	fee = (long) (0.01 * Math.pow(10, 8));
	}
	

}
