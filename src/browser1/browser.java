package browser1;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class browser {

	protected Shell shell;

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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(240, 240, 240));
		shell.setSize(1230, 749);
		shell.setText("SWT Application");
		shell.setLayout(new FormLayout());
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.setText("\u6587\u4EF6");
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.setText("\u7F16\u8F91");
		
		MenuItem mntmNewItem_1 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_1.setText("\u5173\u4E8E");
		
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		FormData fd_tabFolder = new FormData();
		fd_tabFolder.top = new FormAttachment(0, 97);
		fd_tabFolder.left = new FormAttachment(0);
		fd_tabFolder.right = new FormAttachment(100);
		tabFolder.setLayoutData(fd_tabFolder);
		
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("New Item");
		
		Browser browser = new Browser(tabFolder, SWT.NONE);
		tabItem.setControl(browser);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		fd_tabFolder.bottom = new FormAttachment(lblNewLabel_1, -6);
		FormData fd_lblNewLabel_1 = new FormData();
		fd_lblNewLabel_1.left = new FormAttachment(0);
		fd_lblNewLabel_1.right = new FormAttachment(100);
		fd_lblNewLabel_1.bottom = new FormAttachment(100, -3);
		lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new GridLayout(8, false));
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(tabFolder, -97, SWT.TOP);
		fd_composite.left = new FormAttachment(0);
		fd_composite.bottom = new FormAttachment(tabFolder, -6);
		fd_composite.right = new FormAttachment(100, -10);
		composite.setLayoutData(fd_composite);
		
		Button btnBack = new Button(composite, SWT.NONE);
		btnBack.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		GridData gd_btnBack = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnBack.widthHint = 47;
		gd_btnBack.heightHint = 40;
		btnBack.setLayoutData(gd_btnBack);
		btnBack.setText("<");
		
		Button btnForword = new Button(composite, SWT.NONE);
		GridData gd_btnForword = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnForword.widthHint = 47;
		gd_btnForword.heightHint = 40;
		btnForword.setLayoutData(gd_btnForword);
		btnForword.setText(">");
		
		Button btnRefresh = new Button(composite, SWT.NONE);
		btnRefresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		GridData gd_btnRefresh = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnRefresh.heightHint = 40;
		gd_btnRefresh.widthHint = 47;
		btnRefresh.setLayoutData(gd_btnRefresh);
		btnRefresh.setText("\u21BA");
		
		Combo combo_URL = new Combo(composite, SWT.NONE);
		combo_URL.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnStop = new Button(composite, SWT.NONE);
		GridData gd_btnStop = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnStop.heightHint = 40;
		gd_btnStop.widthHint = 40;
		btnStop.setLayoutData(gd_btnStop);
		btnStop.setText("\u2573");
		
		Button btnPage = new Button(composite, SWT.NONE);
		GridData gd_btnPage = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnPage.heightHint = 40;
		gd_btnPage.widthHint = 40;
		btnPage.setLayoutData(gd_btnPage);
		btnPage.setText("\u2302");
		
		Button btnAdd = new Button(composite, SWT.NONE);
		GridData gd_btnAdd = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnAdd.heightHint = 40;
		gd_btnAdd.widthHint = 40;
		btnAdd.setLayoutData(gd_btnAdd);
		btnAdd.setText("+");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("IP:");
		
		Combo combo_IP = new Combo(composite, SWT.NONE);
		GridData gd_combo_IP = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_combo_IP.heightHint = 30;
		combo_IP.setLayoutData(gd_combo_IP);
		
		Button btnGetIP = new Button(composite, SWT.NONE);
		GridData gd_btnGetIP = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnGetIP.widthHint = 40;
		gd_btnGetIP.heightHint = 30;
		btnGetIP.setLayoutData(gd_btnGetIP);
		btnGetIP.setText("GetIp");
		
		Button btnPing = new Button(composite, SWT.NONE);
		GridData gd_btnPing = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnPing.heightHint = 40;
		gd_btnPing.widthHint = 40;
		btnPing.setLayoutData(gd_btnPing);
		btnPing.setText("Ping");
		
		Button btnHistory = new Button(composite, SWT.NONE);
		btnHistory.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 8, SWT.NORMAL));
		GridData gd_btnHistory = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnHistory.heightHint = 40;
		gd_btnHistory.widthHint = 40;
		btnHistory.setLayoutData(gd_btnHistory);
		btnHistory.setText("History");
		
		Button btnSource = new Button(composite, SWT.NONE);
		btnSource.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 8, SWT.NORMAL));
		GridData gd_btnSource = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnSource.widthHint = 40;
		gd_btnSource.heightHint = 40;
		btnSource.setLayoutData(gd_btnSource);
		btnSource.setText("Source");
	}
}


