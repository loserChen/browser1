package browser1;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Decorations;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.CloseWindowListener;
import org.eclipse.swt.browser.LocationAdapter;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.OpenWindowListener;
import org.eclipse.swt.browser.ProgressAdapter;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.StatusTextEvent;
import org.eclipse.swt.browser.StatusTextListener;
import org.eclipse.swt.browser.TitleEvent;
import org.eclipse.swt.browser.TitleListener;
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.ProgressBar;

public class browser {

	protected Shell shlBrowser;
	private String Source;
	private String url;
	private String homePage = "www.zjut.edu.cn";
	private TabFolder tabFolder;
	private FormData fd_composite;
	private FormData fd_tabFolder;
	private Menu menu;
	private MenuItem mntmNewItem;
	private MenuItem menuItem;
	private MenuItem mntmNewItem_1;
	private TabItem tabItem;
	private Browser browser;
	private Composite composite;
	private Button btnBack;
	private GridData gd_btnBack;
	private Button btnForword;
	private GridData gd_btnForword;
	private Button btnStop;
	private GridData gd_btnStop;
	private Combo combo_URL;
	private Button btnRefresh;
	private GridData gd_btnRefresh;
	private Button btnPage;
	private GridData gd_btnPage;
	private Button btnAdd;
	private GridData gd_btnAdd;
	private Label lblNewLabel;
	private Combo combo_IP;
	private GridData gd_combo_IP;
	private Button btnGetIP;
	private GridData gd_btnGetIP;
	private Button btnPing;
	private GridData gd_btnPing;
	private Button btnHistory;
	private GridData gd_btnHistory;
	private Button btnSource;
	private GridData gd_btnSource;
	private Label lblStatus;
	private FormData fd_lblStatus;
	private ProgressBar progressBar;
	private FormData fd_progressBar;
	private boolean loadCompleted;
	private String newUrl;
	private boolean openNewItem=true;
	private Display display;
	private String DEFAULT_BLANK_URL="about:blank"; 
	private String Ping;								//Ping
	java.util.List ping = new ArrayList(); 	
	java.util.List collection = new ArrayList();// 保存历史记录的列表
	private TabItem tabItem_1;
	private Browser browser_default;
	private TabItem tabitem_default;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			browser window = new browser();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlBrowser.open();
		shlBrowser.layout();
		while (!shlBrowser.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlBrowser = new Shell();
		shlBrowser.setBackground(SWTResourceManager.getColor(240, 240, 240));
		shlBrowser.setSize(1230, 749);
		shlBrowser.setText("Browser");
		shlBrowser.setLayout(new FormLayout());
		
		initMenu();
		initBrowser();
		initTool();
		initStatus();
		method();
		
		
	}
	private void initMenu(){
		menu = new Menu(shlBrowser, SWT.BAR);
		shlBrowser.setMenuBar(menu);
		
		menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.setText("\u6587\u4EF6");
		
		mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.setText("\u7F16\u8F91");
		
		mntmNewItem_1 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_1.setText("\u5173\u4E8E");
	}
	private void initBrowser(){
		tabFolder = new TabFolder(shlBrowser, SWT.NONE);
		fd_tabFolder = new FormData();
		fd_tabFolder.left = new FormAttachment(0);
		fd_tabFolder.right = new FormAttachment(100);
		tabFolder.setLayoutData(fd_tabFolder);
		
		tabitem_default = new TabItem(tabFolder, SWT.NONE);
		
		browser_default = new Browser(tabFolder, SWT.NONE);
		tabitem_default.setControl(browser_default);
		url=homePage;
		browser_default.setUrl(url);
		tabFolder.setSelection(tabitem_default);
	}
	private void initTool(){
		composite = new Composite(shlBrowser, SWT.NONE);
		fd_tabFolder.top = new FormAttachment(0, 97);
		composite.setLayout(new GridLayout(8, false));
		fd_composite = new FormData();
		fd_composite.top = new FormAttachment(0);
		fd_composite.left = new FormAttachment(0);
		fd_composite.bottom = new FormAttachment(tabFolder, -6);
		fd_composite.right = new FormAttachment(100, -10);
		composite.setLayoutData(fd_composite);
		
		btnBack = new Button(composite, SWT.NONE);
		gd_btnBack = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnBack.widthHint = 47;
		gd_btnBack.heightHint = 40;
		btnBack.setLayoutData(gd_btnBack);
		btnBack.setText("<");
		
		btnForword = new Button(composite, SWT.NONE);
		gd_btnForword = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnForword.widthHint = 47;
		gd_btnForword.heightHint = 40;
		btnForword.setLayoutData(gd_btnForword);
		btnForword.setText(">");
		
		btnStop = new Button(composite, SWT.NONE);
		gd_btnStop = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnStop.heightHint = 40;
		gd_btnStop.widthHint = 47;
		btnStop.setLayoutData(gd_btnStop);
		btnStop.setText("\u2573");
		
		combo_URL = new Combo(composite, SWT.NONE);
		combo_URL.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnRefresh = new Button(composite, SWT.NONE);
		gd_btnRefresh = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnRefresh.heightHint = 40;
		gd_btnRefresh.widthHint = 40;
		btnRefresh.setLayoutData(gd_btnRefresh);
		btnRefresh.setText("\u21BA");
		
		btnPage = new Button(composite, SWT.NONE);
		gd_btnPage = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnPage.heightHint = 40;
		gd_btnPage.widthHint = 40;
		btnPage.setLayoutData(gd_btnPage);
		btnPage.setText("\u2302");
		
		btnAdd = new Button(composite, SWT.NONE);
		gd_btnAdd = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnAdd.heightHint = 40;
		gd_btnAdd.widthHint = 40;
		btnAdd.setLayoutData(gd_btnAdd);
		btnAdd.setText("+");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("IP:");
		
		combo_IP = new Combo(composite, SWT.NONE);
		gd_combo_IP = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_combo_IP.heightHint = 30;
		combo_IP.setLayoutData(gd_combo_IP);
		
		btnGetIP = new Button(composite, SWT.NONE);
		gd_btnGetIP = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnGetIP.widthHint = 40;
		gd_btnGetIP.heightHint = 30;
		btnGetIP.setLayoutData(gd_btnGetIP);
		btnGetIP.setText("Ip");
		
		btnPing = new Button(composite, SWT.NONE);
		gd_btnPing = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnPing.heightHint = 40;
		gd_btnPing.widthHint = 40;
		btnPing.setLayoutData(gd_btnPing);
		btnPing.setText("Ping");
		
		btnHistory = new Button(composite, SWT.NONE);
		btnHistory.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 6, SWT.NORMAL));
		gd_btnHistory = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnHistory.heightHint = 40;
		gd_btnHistory.widthHint = 40;
		btnHistory.setLayoutData(gd_btnHistory);
		btnHistory.setText("History");
		
		btnSource = new Button(composite, SWT.NONE);
		btnSource.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 6, SWT.NORMAL));
		gd_btnSource = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnSource.widthHint = 40;
		gd_btnSource.heightHint = 40;
		btnSource.setLayoutData(gd_btnSource);
		btnSource.setText("Source");
	}
	private void initStatus(){
		lblStatus = new Label(shlBrowser, SWT.NONE);
		fd_tabFolder.bottom = new FormAttachment(lblStatus, -6);
		fd_lblStatus = new FormData();
		fd_lblStatus.left = new FormAttachment(0);
		fd_lblStatus.right = new FormAttachment(100, -533);
		fd_lblStatus.bottom = new FormAttachment(100, -3);
		lblStatus.setLayoutData(fd_lblStatus);
		
		progressBar = new ProgressBar(shlBrowser, SWT.NONE);
		fd_progressBar = new FormData();
		fd_progressBar.top = new FormAttachment(tabFolder, 6);
		fd_progressBar.left = new FormAttachment(lblStatus, 132);
		progressBar.setLayoutData(fd_progressBar);
		progressBar.setVisible(false);// 打开过程初始不可见
	}
	private void method(){
		 btnBack.setEnabled(false);
		 btnForword.setEnabled(false);
		 btnStop.setEnabled(false);
		 tabItem=tabFolder.getItem(tabFolder.getSelectionIndex());
		 browser=(Browser) tabItem.getControl();
		 
		tabFolder.addMouseListener(new MouseAdapter()
 		{
 			@Override
 			public void mouseUp(MouseEvent e) {
 				if(e.button==3){//右键
 					Menu menu_itemRightMouse=new Menu(shlBrowser,SWT.POP_UP);
 					tabFolder.setMenu(menu_itemRightMouse);
 					MenuItem menuItem_itemClose=new MenuItem(menu_itemRightMouse,SWT.NONE);
 					menuItem_itemClose.setText("关闭当前标签");
 					menuItem_itemClose.addSelectionListener(new SelectionAdapter()
 					{
 						@Override
 						public void widgetSelected(SelectionEvent e) 
 						{
 							if(tabFolder.getItemCount()!=1)
 							{
 								//不是只存在一个标签的情况下
 								
 								tabItem.dispose();
 							}
 							else
 							{
 								//只有一个标签
 								browser.setUrl(":blank");
 								browser.setText("");
 							}
 						}
 					});
 					MenuItem menuItem_itemCloseAll=new MenuItem(menu_itemRightMouse,SWT.NONE);
 					menuItem_itemCloseAll.setText("关闭所有标签");
 					menuItem_itemCloseAll.addSelectionListener(new SelectionAdapter()
 					{
 						@Override
 						public void widgetSelected(SelectionEvent e) 
 						{
 							shlBrowser.close();
 						}
 					});
 					MenuItem menuItem_openNewItem=new MenuItem(menu_itemRightMouse, SWT.NONE);
 					menuItem_openNewItem.setText("打开新选项卡");
 					menuItem_openNewItem.addSelectionListener(new SelectionAdapter() {
 						public void widgetSelected(SelectionEvent e)
 						{
 							openNewBrowserTab();
 						}
					});
 				}
 			}
 		});
		 tabFolder.addMouseListener(new MouseAdapter() {  
	            // 双击关闭选中的浏览器窗口  
	            public void mouseDoubleClick(final MouseEvent e) {
	            	TabItem tab = tabFolder.getItem(new Point(e.x, e.y)); 
	            	if(tabFolder.getItemCount()!=1)
						{
							//不是只存在一个标签的情况下
							
							tab.dispose();
						}
						else
						{
							//只有一个标签
							browser.setUrl(":blank");
							browser.setText("");
						}
	            }  
	        });  
		browser.addLocationListener(new LocationAdapter() 
		  {
			  

			@Override
			  public void changing(LocationEvent e) 
			  {
				  // 表示超级链接地址改变了
				  if(openNewItem==false)
				  {
					  //新的页面在同一标签中打开
					  btnBack.setEnabled(true);				//后退按钮可用,此句是后退按钮可用判定的逻辑开始点
				  }
			  }

			  @Override
			  public void changed(LocationEvent e) 
			  {
				  // 找到了页面链接地址
				  combo_URL.setText(e.location);			// 改变链接地址显示
				  /**
				   * 新的页面已经打开,browser的LocationListener已经监听完毕,openNewItem回复默认值
				   */
				  if(openNewItem==true)
				  {
					  openNewItem=false;
				  }
			  }
		  	});
		/**
	  	 * 显示页面的提示语句，在新的页面导入时发生
	  	 */
	  	browser.addTitleListener(new TitleListener() 
	  	{
	  		@Override
	  		public void changed(TitleEvent e) 
	  		{
	  			TabItem item =tabFolder.getItem(tabFolder.getSelectionIndex());
	  			item.setText(e.title);
	  			item.setToolTipText(e.title);//标签显示提示符
	  		}
	  	});
	  	combo_URL.addKeyListener(new KeyAdapter() {// 手动输入地址栏后，按回车键转到相应网址
	  	   @Override
	  	   public void keyReleased(KeyEvent e) {
	  	    if (e.keyCode == SWT.CR) {//回车键触发事件
	  	     TabItem item=tabFolder.getItem(tabFolder.getSelectionIndex());
	  	     browser=(Browser) item.getControl();
	  	     browser.setUrl(combo_URL.getText());
	  	    }
	  	   }
	  	  });
	  	browser.addProgressListener(new ProgressAdapter() {
	  	   
		@Override
	  	   public void changed(ProgressEvent e) {//本事件不断发生于页面的导入过程中
	  	    progressBar.setMaximum(e.total);// e.total表示从最开始页面到最终页面的数值
	  	    progressBar.setSelection(e.current);
	  	    if (e.current != e.total) {//页面还没完全导入
	  	    	loadCompleted=false;
	  	     progressBar.setVisible(true);// 页面的导入情况栏可见
	  	     btnStop.setEnabled(true);
	  	    } else {
	  	    	btnStop.setEnabled(false);
	  	    	loadCompleted=true;
	  	     progressBar.setVisible(false);// 页面导入情况栏不可见
	  	    }
	  	   }
	  	   @Override
	  	   public void completed(ProgressEvent arg0) {//发生在一次导入页面时,本监听器changed事件最后一次发生之前
	  	   url=combo_URL.getText();
	  	   addUrlToCombo(combo_URL.getText());
	  	   save(url);
	  	   }
	  	  });
	  	browser.addStatusTextListener(new StatusTextListener() {
	  	   

		public void changed(StatusTextEvent e) {
	  	    if (loadCompleted == false) {
	  	     lblStatus.setText(e.text);
	  	    } 
	  	    else {
				newUrl=e.text;
			}
	  	   }
	  	  });
	  	browser.addOpenWindowListener(new OpenWindowListener() {// 在当前页面中打开点击的链接页面
	  	 
			public void open(WindowEvent e) {
	  	      Browser browser_new = new Browser(tabFolder, SWT.NONE);
	  	      TabItem tabItem_new = new TabItem(tabFolder, SWT.NONE);
	  	      tabItem_new.setControl(browser_new);
	  	      tabFolder.setSelection(tabItem_new);//新打开的页面标签置顶
	  	      tabFolder.redraw();//刷新容器
	  	      openNewItem=true;//新的页面在新的标签中打开
	  	      e.browser = browser_new;
	  	    e.display.asyncExec(new Runnable(){
	  	       public void run() {
	  	        method(); 
	  	       }
	  	      });
	  	     }
	  	    });
	  	browser.addCloseWindowListener(new CloseWindowListener(){
	  	   public void close(WindowEvent e) {
	  		 TabItem item=tabFolder.getItem(tabFolder.getSelectionIndex());
	  	     browser=(Browser) item.getControl();
	  	    browser.dispose();
	  	   }
	  	  });
	  	 btnBack.addSelectionListener(new SelectionAdapter() {
	  	   @Override
	  	   public void widgetSelected(SelectionEvent arg0) {
	  	    if (browser.isBackEnabled()){//本次可后退
	  	     browser.back();
	  	     btnForword.setEnabled(true);//下次可前进，前进按钮可用
	  	     //System.out.println("可后退");//调试语句
	  	    }
	  	    if(!browser.isBackEnabled()){//下次不可后退，后退按钮不可用
	  	     btnBack.setEnabled(false);
	  	    }
	  	   }
	  	  });
	  	 btnForword.addSelectionListener(new SelectionAdapter() {
	  		 public void widgetSelected(SelectionEvent arg0){
	  			if (browser.isForwardEnabled()){//本次可前进
	  			     browser.forward();
	  			     btnBack.setEnabled(true);//后退按钮可用
	  			     //System.out.println("可向前");//调试语句
	  			    }
	  			    if(!browser.isForwardEnabled()){//下次不可前进，前进按钮不可用
	  			     btnForword.setEnabled(false);     
	  			    }
	  		 }
		});
	  	btnStop.addSelectionListener(new SelectionAdapter() {
	  	   @Override
	  	   public void widgetSelected(SelectionEvent arg0) {
	  	    browser.stop();
	  	   }
	  	  });
	  	btnRefresh.addSelectionListener(new SelectionAdapter() {
	  		@Override
	  		public void widgetSelected(SelectionEvent arg0){
	  			if(combo_URL.getText()!=null){
	  				url=combo_URL.getText();
	  				browser.setUrl(url);
	  			}
	  			else{
	  				browser.setUrl(homePage);
	  			}
	  		}
		});
		btnPage.addSelectionListener(new SelectionAdapter() {
			@Override
	  		public void widgetSelected(SelectionEvent arg0){
	  				browser.setUrl(homePage);
	  		}
		});
		btnGetIP.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0){
				url=combo_URL.getText();
				if(url!=null)
				{
					String url1=url.replace("http://", "").trim();
					String url2=url1.replace("https://", "").trim();
					String url3=url2.replace("/", "").trim();
					InetAddress iAddress = null;
					try 
					{
						iAddress = InetAddress.getByName(url3);
						//在comboIp中输入得到的IP
						combo_IP.setText(iAddress.getHostAddress().toString());
						addIpToCombo(iAddress.getHostAddress().toString());
					} 
					catch (UnknownHostException e1) 
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		 combo_URL.addSelectionListener(new SelectionListener() {
	            public void widgetSelected(SelectionEvent evt) {
	                browser.setUrl(combo_URL.getText());
	            }
	 
	            public void widgetDefaultSelected(SelectionEvent evt) {
	 
	            }
	        });
		 btnHistory.addSelectionListener(new SelectionAdapter() {
			 public void widgetSelected(SelectionEvent arg0){
				 try {
					new outPutHistory(readHistory());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		});
		 btnSource.addSelectionListener(new SelectionAdapter() 
		  {
			  @Override
				public void widgetDefaultSelected(SelectionEvent arg0) 
				{
					// TODO 自动生成的方法存根
					
				}
				@Override
				public void widgetSelected(SelectionEvent arg0) 
				{
					url=combo_URL.getText();
					try  {
			            getSource(url);
			        }  catch (Exception e1) 
			        {
			            // TODO Auto-generated catch block
			            e1.printStackTrace();
			        }
					//弹出信息面板显示浏览记录
					try 
					{
						new outPutHistory(Source);
					} 
					catch (IOException e) 
					{
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
		  });
		//获取Ping
		  btnPing.addSelectionListener(new SelectionAdapter() 
		  {
			  @Override
				public void widgetSelected(SelectionEvent e) 
			  {

					String serverIP = "127.0.0.1";

					try {
						Runtime ce = Runtime.getRuntime();
						InputStream in = (InputStream) ce.exec("ping " + serverIP).getInputStream();
						BufferedInputStream bin = new BufferedInputStream(in);
						byte pingInfo[] = new byte[100];
						int n;
						while ((n = bin.read(pingInfo, 0, 100)) != -1) 
						{
							Ping = null;
							Ping = new String(pingInfo, 0, n);
							ping.add(Ping);
						}
						ping.add("Over!\n\n");
						try 
						{
							//Ping=Ping+"Over!\n\n";
							new outPutHistory(ping.toString());
						} 
						catch (IOException e1) 
						{
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
						
					} catch (Exception ee) {
						System.out.println(ee);
					}


				}
		  });
		  btnAdd.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String Address=combo_URL.getText().trim();
					try {
						Runtime ce = Runtime.getRuntime();
						InputStream in;
						in = (InputStream) ce.exec("ping " + Address).getInputStream();
						BufferedInputStream bin = new BufferedInputStream(in);
						byte pingInfo[] = new byte[100];
						int n;
						while ((n = bin.read(pingInfo, 0, 100)) != -1) 
						{
							Ping = null;
							Ping = new String(pingInfo, 0, n);
							collection.add(Address);
						}
						new outPutHistory(collection.toString());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
	}
	public void save(String history) 
	{
        try 
        {
        	Date data = new Date(); 
        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        String time=dateFormat.format(data); 
        	String filePath="History.txt";
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append(time+"  "+history+"\r\n");
            bw.close();
            fw.close();
        } 
        catch (Exception e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	//读取记录从text中
	public String readHistory() 
	{
        try 
        {
        	String filePath="History.txt";
            FileReader fw = new FileReader(filePath);
            BufferedReader rw=new BufferedReader(fw);      
            int temp=0;   
            String str="";  
            while ((temp=rw.read())!=-1) 
            {      
              str+=(char)temp;  
            }
            rw.close();
            fw.close();
            return str;
        } 
        catch (Exception e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
	}
	
	//新建窗口输出记录
	class outPutHistory extends JFrame 
	{
	    private static final long serialVersionUID = 1L;
	    JTextArea ta = null;
	    JScrollPane jsp = null;
	    //只要一个参数
	    outPutHistory(String txt) throws IOException 
	    {
	        this.setVisible(true);
	        this.setBounds(500, 200, 800, 600);
	        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	        ta = new JTextArea();
	        jsp = new JScrollPane(ta);
	        ta.setText(txt);
	        add(jsp);
	        validate();
	    }
	} 
	 private Browser openNewBrowserTab() {  
		    Browser browser_new = new Browser(tabFolder, SWT.NONE);
		    initialize(browser); 
 	        TabItem tabItem_new = new TabItem(tabFolder, SWT.NONE);
 	        tabItem_new.setControl(browser_new);
	        tabFolder.setSelection(tabItem_new);      
	        tabItem_new.setText(DEFAULT_BLANK_URL);  
	        tabItem_new.setToolTipText(DEFAULT_BLANK_URL);  
	        combo_URL.setText(DEFAULT_BLANK_URL);  
	        combo_URL.setFocus();  
	        openNewItem=true;
	        return browser;
	    }  
	 private void initialize(final Browser browser) {  
	        browser.addOpenWindowListener(new OpenWindowListener() {    //打开一个新的浏览器窗口事件  
	            public void open(WindowEvent event) {  
	                event.browser = openNewBrowserTab();  
	                event.display.asyncExec(new Runnable(){
	     	  	       public void run() {
	     	  	        method(); 
	     	  	       }
	     	  	      });
	            }  
	        });  
	 }
	 private void addUrlToCombo(String url){  
	        if(!DEFAULT_BLANK_URL.equals(url)){  
	            int index = combo_URL.indexOf(url);   
	            if(index == -1){  
	                combo_URL.add(url, 0);  
	            }  
	            combo_URL.select(0);  
	            if(combo_URL.getItemCount() > 50){  
	                combo_URL.remove(50, combo_URL.getItemCount()-1);  
	            }  
	        }  
	    } 
	 private void addIpToCombo(String ip){
		 int index=combo_IP.indexOf(ip);
		 if(index ==-1){
			 combo_IP.add(ip,0);
			 combo_IP.select(0);
			 if(combo_IP.getItemCount()>50){
				 combo_IP.remove(50,combo_IP.getItemCount()-1);
			 }
			 
		 }
	 }
	//获取源代码
		public void getSource (String url) 
		{
			try 
			{
				String linesep,htmlLine;
				linesep=System.getProperty("line.separator");
				Source="";
				//根据URL得到的源代码
				java.net.URL source =new URL(url);  
				InputStream in=new BufferedInputStream(source.openStream());
				BufferedReader br=new BufferedReader(new InputStreamReader(in));
				while((htmlLine=br.readLine())!=null)
				{
					Source=Source+htmlLine+linesep;
				}
				
			 } 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}