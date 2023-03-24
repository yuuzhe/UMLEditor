package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UMLEditor {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UMLEditor window = new UMLEditor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UMLEditor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		// We don't have auto resizing functionality.
		frame.setResizable(false);
		frame.setBounds(100, 100, 1388, 836);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenu editMenu = new JMenu("Edit");
		menuBar.add(editMenu);
		
		Canvas canvas = new Canvas();

		JMenuItem groupMI = new JMenuItem("Group");
		 groupMI.setEnabled(false);
		editMenu.add(groupMI);
		groupMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompositeObject co = new CompositeObject(canvas.getSelected());
				canvas.add(co, 0);
				canvas.repaint();
				groupMI.setEnabled(false);
			}
		});
		
		JMenuItem ungroupMI = new JMenuItem("Ungroup");
		ungroupMI.setEnabled(false);
		editMenu.add(ungroupMI);
		ungroupMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompositeObject co = (CompositeObject) canvas.getSelected().get(0);
				co.ungroup();
				groupMI.setEnabled(false);
			}
		});
		
		JMenuItem renameMI = new JMenuItem("Change Object Name");
		renameMI.setEnabled(false);
		renameMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(renameMI, "Change Object Name:", null);
				BasicObject bo = (BasicObject) canvas.getSelected().get(0);
				bo.setText(name);
			}
		});
		editMenu.add(renameMI);
		
		ButtonsPanel buttonsPanel = new ButtonsPanel();
		buttonsPanel.setBackground(new Color(255, 128, 128));
		
		canvas.setBackground(new Color(255, 255, 255));
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(buttonsPanel, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
					.addComponent(canvas, GroupLayout.PREFERRED_SIZE, 1270, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(buttonsPanel, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
						.addComponent(canvas, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE))
					.addContainerGap())
		);
		GroupLayout gl_canvas = new GroupLayout(canvas);
		gl_canvas.setHorizontalGroup(
			gl_canvas.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1270, Short.MAX_VALUE)
		);
		gl_canvas.setVerticalGroup(
			gl_canvas.createParallelGroup(Alignment.LEADING)
				.addGap(0, 764, Short.MAX_VALUE)
		);
		canvas.setLayout(gl_canvas);
		
		// Select State
		JToggleButton selectButton = new JToggleButton();
		selectButton.setSize(64, 64);
		selectButton.setToolTipText("Select");
		selectButton.setIcon(new ImageIcon("assets\\select.png"));
		selectButton.setSelected(true);
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.setState(new SelectState(canvas));
				canvas.setVL(null);
			}
		});
		buttonsPanel.add(selectButton);
		buttonsPanel.addtoButtonGroup(selectButton);
		
		// Association Line State
		JToggleButton associationLineButton = new JToggleButton();
		associationLineButton.setSize(64, 64);
		associationLineButton.setToolTipText("Association Line");
		associationLineButton.setIcon(new ImageIcon("assets\\association.png"));
		associationLineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.setState(new DrawLineState());
				canvas.setVL(new AL());
			}
		});
		buttonsPanel.add(associationLineButton);
		buttonsPanel.addtoButtonGroup(associationLineButton);
		
		// Generalization Line State
		JToggleButton generalizationLineButton = new JToggleButton();
		generalizationLineButton.setSize(64, 64);
		generalizationLineButton.setToolTipText("Generalization Line");
		generalizationLineButton.setIcon(new ImageIcon("assets\\generalization.png"));
		generalizationLineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.setState(new DrawLineState());
				canvas.setVL(new GL());
			}
		});
		buttonsPanel.add(generalizationLineButton);
		buttonsPanel.addtoButtonGroup(generalizationLineButton);
		
		// Composition Line State
		JToggleButton compositionLineButton = new JToggleButton();
		compositionLineButton.setSize(64, 64);
		compositionLineButton.setIcon(new ImageIcon("assets\\composition.png"));
		compositionLineButton.setToolTipText("Composition Line");
		compositionLineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.setState(new DrawLineState());
				canvas.setVL(new CL());
			}
		});
		buttonsPanel.add(compositionLineButton);
		buttonsPanel.addtoButtonGroup(compositionLineButton);
		
		// Class State
		JToggleButton classButton = new JToggleButton();
		classButton.setSize(64, 64);
		classButton.setToolTipText("Class");
		classButton.setIcon(new ImageIcon("assets\\class.png"));
		classButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.setState(new ClassState());
				canvas.setVL(null);
			}
		});
		buttonsPanel.add(classButton);
		buttonsPanel.addtoButtonGroup(classButton);
		
		// Use Case State
		JToggleButton useCaseButton = new JToggleButton();
		useCaseButton.setSize(64, 64);
		useCaseButton.setToolTipText("Use Case");
		useCaseButton.setIcon(new ImageIcon("assets\\uc.png"));
		useCaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.setState(new UCState());
				canvas.setVL(null);
			}
		});
		buttonsPanel.add(useCaseButton);
		buttonsPanel.addtoButtonGroup(useCaseButton);
		frame.getContentPane().setLayout(groupLayout);

		UIComponent.setCanvas(canvas);
		UIComponent.setGroupMI(groupMI);
		UIComponent.setUngroupMI(ungroupMI);
		UIComponent.setRenameMI(renameMI);
	}
}