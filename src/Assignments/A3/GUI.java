package Assignments.A3;

import java.awt.*;

import javax.swing.*;

/**
 * The GUI for assignment 3
 */
public class GUI {
	/**
	 * These are the components you need to handle. You have to add listeners and/or
	 * code Static controls are defined inline
	 */
	private JFrame frame; // The Main window
	private JProgressBar bufferStatus; // The progressbar, showing content in buffer

	// Data for Producer Scan
	private JButton btnStartS; // Button start Scan
	private JButton btnStopS; // Button stop Scan
	private JLabel lblStatusS; // Status Scan
	// DAta for producer Arla
	private JButton btnStartA; // Button start Arla
	private JButton btnStopA; // Button stop Arla
	private JLabel lblStatusA; // Status Arla
	// Data for producer AxFood
	private JButton btnStartX; // Button start AxFood
	private JButton btnStopX; // Button stop AxFood
	private JLabel lblStatusX; // Status AxFood

	// Data for consumer ICA
	private JLabel lblIcaItems; // Ica limits
	private JLabel lblIcaWeight;
	private JLabel lblIcaVolume;
	private JLabel lblIcaStatus; // load status
	private JTextArea lstIca; // The cargo list
	private JButton btnIcaStart; // The buttons
	private JButton btnIcaStop;
	public JCheckBox chkIcaCont; // Continue checkbox
	// Data for consumer COOP
	private JLabel lblCoopItems;
	private JLabel lblCoopWeight;
	private JLabel lblCoopVolume;
	private JLabel lblCoopStatus; // load status
	private JTextArea lstCoop; // The cargo list
	private JButton btnCoopStart; // The buttons
	private JButton btnCoopStop;
	public JCheckBox chkCoopCont; // Continue checkbox
	// Data for consumer CITY GROSS
	private JLabel lblCGItems;
	private JLabel lblCGWeight;
	private JLabel lblCGVolume;
	private JLabel lblCGStatus; // load status
	private JTextArea lstCG; // The cargo list
	private JButton btnCGStart; // The buttons
	private JButton btnCGStop;
	public JCheckBox chkCGCont; // Continue checkbox

	private Controller controller;
	
	/**
	 * Constructor, creates the window
	 * @param controller 
	 */
	public GUI(Controller controller) {
		this.controller = controller;
		Start();
	}

	public void setValues(String name, int items, double weight, double volume, String status, String itemList) {
		
		
		if(name.equals("ICA")) {
			lblIcaItems.setText(items + "");
			lblIcaWeight.setText(weight + "");;
			lblIcaVolume.setText(volume + "");;
			lblIcaStatus.setText("Status: " + status);
			lstIca.setText(itemList);
		} else if(name.equals("COOP")) {
			
		} else if(name.equals("CG"))   {
			
		}
	}
	
	/**
	 * Starts the application
	 */
	public void Start() {
		frame = new JFrame();
		frame.setBounds(0, 0, 730, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Food Supply System");
		InitializeGUI(); // Fill in components
		initButtons();
		frame.setVisible(true);
		frame.setResizable(false); // Prevent user from change size
		frame.setLocationRelativeTo(null); // Start middle screen
	}

	/**
	 * Sets up the GUI with components
	 */
	private void InitializeGUI() {
		// First create the three main panels
		JPanel pnlBuffer = new JPanel();
		pnlBuffer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Storage"));
		pnlBuffer.setBounds(13, 403, 693, 82);
		pnlBuffer.setLayout(null);
		// Then create the progressbar, only component in buffer panel
		bufferStatus = new JProgressBar();
		bufferStatus.setBounds(155, 37, 500, 23);
		bufferStatus.setBorder(BorderFactory.createLineBorder(Color.black));
		bufferStatus.setForeground(Color.GREEN);
		pnlBuffer.add(bufferStatus);
		JLabel lblmax = new JLabel("Max capacity: " + controller.storageMax); 
		lblmax.setBounds(10, 42, 126, 13);
		pnlBuffer.add(lblmax);
		frame.add(pnlBuffer);

		JPanel pnlProd = new JPanel();
		pnlProd.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Producers"));
		pnlProd.setBounds(13, 13, 229, 379);
		pnlProd.setLayout(null);

		JPanel pnlCons = new JPanel();
		pnlCons.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Consumers"));
		pnlCons.setBounds(266, 13, 440, 379);
		pnlCons.setLayout(null);

		// Now add the three panels to producer panel
		JPanel pnlScan = new JPanel();
		pnlScan.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Producer: Scan"));
		pnlScan.setBounds(6, 19, 217, 100);
		pnlScan.setLayout(null);

		// Content Scan panel
		btnStartS = new JButton("Start Producing");
		btnStartS.setBounds(10, 59, 125, 23);
		pnlScan.add(btnStartS);
		btnStopS = new JButton("Stop");
		btnStopS.setBounds(140, 59, 65, 23);
		pnlScan.add(btnStopS);
		lblStatusS = new JLabel("Staus Idle/Stop/Producing");
		lblStatusS.setBounds(10, 31, 200, 13);
		pnlScan.add(lblStatusS);
		// Add Scan panel to producers
		pnlProd.add(pnlScan);

		// The Arla panel
		JPanel pnlArla = new JPanel();
		pnlArla.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Producer: Arla"));
		pnlArla.setBounds(6, 139, 217, 100);
		pnlArla.setLayout(null);

		// Content Arla panel
		btnStartA = new JButton("Start Producing");
		btnStartA.setBounds(10, 59, 125, 23);
		pnlArla.add(btnStartA);
		btnStopA = new JButton("Stop");
		btnStopA.setBounds(140, 59, 65, 23);
		pnlArla.add(btnStopA);
		lblStatusA = new JLabel("Staus Idle/Stop/Producing");
		lblStatusA.setBounds(10, 31, 200, 13);
		pnlArla.add(lblStatusA);
		// Add Arla panel to producers
		pnlProd.add(pnlArla);

		// The AxFood Panel
		JPanel pnlAxfood = new JPanel();
		pnlAxfood.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Producer: AxFood"));
		pnlAxfood.setBounds(6, 262, 217, 100);
		pnlAxfood.setLayout(null);

		// Content AxFood Panel
		btnStartX = new JButton("Start Producing");
		btnStartX.setBounds(10, 59, 125, 23);
		pnlAxfood.add(btnStartX);
		btnStopX = new JButton("Stop");
		btnStopX.setBounds(140, 59, 65, 23);
		pnlAxfood.add(btnStopX);
		lblStatusX = new JLabel("Staus Idle/Stop/Producing");
		lblStatusX.setBounds(10, 31, 200, 13);
		pnlAxfood.add(lblStatusX);
		// Add Axfood panel to producers
		pnlProd.add(pnlAxfood);
		// Producer panel done, add to frame
		frame.add(pnlProd);

		// Next, add the three panels to Consumer panel
		JPanel pnlICA = new JPanel();
		pnlICA.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Consumer: ICA"));
		pnlICA.setBounds(19, 19, 415, 100);
		pnlICA.setLayout(null);

		// Content ICA panel
		// First the limits panel
		JPanel pnlLim = new JPanel();
		pnlLim.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Package Limits"));
		pnlLim.setBounds(6, 19, 107, 75);
		pnlLim.setLayout(null);
		JLabel lblItems = new JLabel("Items:");
		lblItems.setBounds(7, 20, 50, 13);
		pnlLim.add(lblItems);
		JLabel lblWeight = new JLabel("Weight:");
		lblWeight.setBounds(7, 35, 50, 13);
		pnlLim.add(lblWeight);
		JLabel lblVolume = new JLabel("Volume:");
		lblVolume.setBounds(7, 50, 50, 13);
		pnlLim.add(lblVolume);
		lblIcaItems = new JLabel("Data");
		lblIcaItems.setBounds(60, 20, 47, 13);
		pnlLim.add(lblIcaItems);
		lblIcaWeight = new JLabel("Data");
		lblIcaWeight.setBounds(60, 35, 47, 13);
		pnlLim.add(lblIcaWeight);
		lblIcaVolume = new JLabel("Data");
		lblIcaVolume.setBounds(60, 50, 47, 13);
		pnlLim.add(lblIcaVolume);
		pnlICA.add(pnlLim);
		// Then rest of controls
		lstIca = new JTextArea();
		lstIca.setEditable(false);
		JScrollPane spane = new JScrollPane(lstIca);
		spane.setBounds(307, 16, 102, 69);
		spane.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlICA.add(spane);
		btnIcaStart = new JButton("Start Loading");
		btnIcaStart.setBounds(118, 64, 120, 23);
		pnlICA.add(btnIcaStart);
		btnIcaStop = new JButton("Stop");
		btnIcaStop.setBounds(240, 64, 60, 23);
		pnlICA.add(btnIcaStop);
		lblIcaStatus = new JLabel("Ica stop status here");
		lblIcaStatus.setBounds(118, 16, 150, 23);
		pnlICA.add(lblIcaStatus);
		chkIcaCont = new JCheckBox("Continue load");
		chkIcaCont.setBounds(118, 39, 130, 17);
		pnlICA.add(chkIcaCont);
		// All done, add to consumers panel
		pnlCons.add(pnlICA);

		JPanel pnlCOOP = new JPanel();
		pnlCOOP.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Consumer: COOP"));
		pnlCOOP.setBounds(19, 139, 415, 100);
		pnlCOOP.setLayout(null);
		pnlCons.add(pnlCOOP);

		// Content COOP panel
		// First the limits panel
		JPanel pnlLimC = new JPanel();
		pnlLimC.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Package Limits"));
		pnlLimC.setBounds(6, 19, 107, 75);
		pnlLimC.setLayout(null);
		JLabel lblItemsC = new JLabel("Items:");
		lblItemsC.setBounds(7, 20, 50, 13);
		pnlLimC.add(lblItemsC);
		JLabel lblWeightC = new JLabel("Weight:");
		lblWeightC.setBounds(7, 35, 50, 13);
		pnlLimC.add(lblWeightC);
		JLabel lblVolumeC = new JLabel("Volume:");
		lblVolumeC.setBounds(7, 50, 50, 13);
		pnlLimC.add(lblVolumeC);
		lblCoopItems = new JLabel("Data");
		lblCoopItems.setBounds(60, 20, 47, 13);
		pnlLimC.add(lblCoopItems);
		lblCoopWeight = new JLabel("Data");
		lblCoopWeight.setBounds(60, 35, 47, 13);
		pnlLimC.add(lblCoopWeight);
		lblCoopVolume = new JLabel("Data");
		lblCoopVolume.setBounds(60, 50, 47, 13);
		pnlLimC.add(lblCoopVolume);
		pnlCOOP.add(pnlLimC);
		// Then rest of controls
		lstCoop = new JTextArea();
		lstCoop.setEditable(false);
		JScrollPane spaneC = new JScrollPane(lstCoop);
		spaneC.setBounds(307, 16, 102, 69);
		spaneC.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlCOOP.add(spaneC);
		btnCoopStart = new JButton("Start Loading");
		btnCoopStart.setBounds(118, 64, 120, 23);
		pnlCOOP.add(btnCoopStart);
		btnCoopStop = new JButton("Stop");
		btnCoopStop.setBounds(240, 64, 60, 23);
		pnlCOOP.add(btnCoopStop);
		lblCoopStatus = new JLabel("Coop stop status here");
		lblCoopStatus.setBounds(118, 16, 150, 23);
		pnlCOOP.add(lblCoopStatus);
		chkCoopCont = new JCheckBox("Continue load");
		chkCoopCont.setBounds(118, 39, 130, 17);
		pnlCOOP.add(chkCoopCont);
		// All done, add to consumers panel
		pnlCons.add(pnlCOOP);

		JPanel pnlCG = new JPanel();
		pnlCG.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Consumer: CITY GROSS"));
		pnlCG.setBounds(19, 262, 415, 100);
		pnlCG.setLayout(null);
		pnlCons.add(pnlCG);

		// Content CITY GROSS panel
		// First the limits panel
		JPanel pnlLimG = new JPanel();
		pnlLimG.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Package Limits"));
		pnlLimG.setBounds(6, 19, 107, 75);
		pnlLimG.setLayout(null);
		JLabel lblItemsG = new JLabel("Items:");
		lblItemsG.setBounds(7, 20, 50, 13);
		pnlLimG.add(lblItemsG);
		JLabel lblWeightG = new JLabel("Weight:");
		lblWeightG.setBounds(7, 35, 50, 13);
		pnlLimG.add(lblWeightG);
		JLabel lblVolumeG = new JLabel("Volume:");
		lblVolumeG.setBounds(7, 50, 50, 13);
		pnlLimG.add(lblVolumeG);
		lblCGItems = new JLabel("Data");
		lblCGItems.setBounds(60, 20, 47, 13);
		pnlLimG.add(lblCGItems);
		lblCGWeight = new JLabel("Data");
		lblCGWeight.setBounds(60, 35, 47, 13);
		pnlLimG.add(lblCGWeight);
		lblCGVolume = new JLabel("Data");
		lblCGVolume.setBounds(60, 50, 47, 13);
		pnlLimG.add(lblCGVolume);
		pnlCG.add(pnlLimG);
		// Then rest of controls
		lstCG = new JTextArea();
		lstCG.setEditable(false);
		JScrollPane spaneG = new JScrollPane(lstCG);
		spaneG.setBounds(307, 16, 102, 69);
		spaneG.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlCG.add(spaneG);
		btnCGStart = new JButton("Start Loading");
		btnCGStart.setBounds(118, 64, 120, 23);
		pnlCG.add(btnCGStart);
		btnCGStop = new JButton("Stop");
		btnCGStop.setBounds(240, 64, 60, 23);
		pnlCG.add(btnCGStop);
		lblCGStatus = new JLabel("CITY GROSS stop status here");
		lblCGStatus.setBounds(118, 16, 150, 23);
		pnlCG.add(lblCGStatus);
		chkCGCont = new JCheckBox("Continue load");
		chkCGCont.setBounds(118, 39, 130, 17);
		pnlCG.add(chkCGCont);
		// All done, add to consumers panel
		pnlCons.add(pnlCOOP);

		// Add consumer panel to frame
		frame.add(pnlCons);
	}
	
	private void initButtons() {
		btnStartS.addActionListener(e -> controller.p1.startProducing());
		btnStopS.addActionListener(e -> controller.p1.stopProducing());
		
		btnStartA.addActionListener(e -> controller.p2.startProducing());
		btnStopA.addActionListener(e -> controller.p2.stopProducing());
		
		btnStartX.addActionListener(e -> controller.p3.startProducing());
		btnStopX.addActionListener(e -> controller.p3.stopProducing());
		
		
		btnIcaStart.addActionListener(e -> controller.c1.startConsuming());
		btnIcaStop.addActionListener(e -> controller.c1.stopConsuming());
		
		btnCoopStart.addActionListener(e -> controller.c2.startConsuming());
		btnCoopStop.addActionListener(e -> controller.c2.stopConsuming());
		
		btnCGStart.addActionListener(e -> controller.c3.startConsuming());
		btnCGStop.addActionListener(e -> controller.c3.stopConsuming());
	}
	
	public void updateStatusBar(int max, int current) {
		bufferStatus.setMinimum(0);
		bufferStatus.setMaximum(max);
		bufferStatus.setValue(current);
	}
}