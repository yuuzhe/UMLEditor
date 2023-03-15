package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

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
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Group");
		editMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Ungroup");
		editMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Change Object Name");
		editMenu.add(mntmNewMenuItem);
		
		ButtonsPanel buttonsPanel = new ButtonsPanel();
		buttonsPanel.setBackground(new Color(255, 128, 128));

		
		Canvas canvas = new Canvas();
		
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
		
		SelectStateButton selectButton = new SelectStateButton();
		selectButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (selectButton.isSelected()) {
					canvas.setState(selectButton);
				}
			}
		});

		selectButton.setSelected(true);
		// Default state
		canvas.setState(selectButton);
		buttonsPanel.add(selectButton);
		buttonsPanel.addtoButtonGroup(selectButton);
		
		ALStateButton associationLineButton = new ALStateButton();
		associationLineButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (associationLineButton.isSelected()) {
					canvas.setState(associationLineButton);
				}
			}
		});
		buttonsPanel.add(associationLineButton);
		buttonsPanel.addtoButtonGroup(associationLineButton);
		
		GLStateButton generalizationLineButton = new GLStateButton();
		generalizationLineButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (generalizationLineButton.isSelected()) {
					canvas.setState(generalizationLineButton);
				}
			}
		});
		buttonsPanel.add(generalizationLineButton);
		buttonsPanel.addtoButtonGroup(generalizationLineButton);
		
		CLStateButton compositionLineButton = new CLStateButton();
		compositionLineButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (compositionLineButton.isSelected()) {
					canvas.setState(compositionLineButton);
				}
			}
		});
		buttonsPanel.add(compositionLineButton);
		buttonsPanel.addtoButtonGroup(compositionLineButton);
		
		ClassStateButton classButton = new ClassStateButton();
		classButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (classButton.isSelected()) {
					canvas.setState(classButton);
				}
			}
		});
		buttonsPanel.add(classButton);
		buttonsPanel.addtoButtonGroup(classButton);
		
		UseCaseStateButton useCaseButton = new UseCaseStateButton();
		useCaseButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (useCaseButton.isSelected()) {
					canvas.setState(useCaseButton);
				}
			}
		});
		buttonsPanel.add(useCaseButton);
		buttonsPanel.addtoButtonGroup(useCaseButton);
		frame.getContentPane().setLayout(groupLayout);
	}
}