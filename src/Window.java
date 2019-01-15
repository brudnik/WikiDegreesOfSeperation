import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
public class Window extends JFrame implements ActionListener{
	private JTextField from, to;
	public JTextArea output;
	private JButton go;
	JRadioButton two = new JRadioButton("2",true);
	JRadioButton three = new JRadioButton("3");
	JRadioButton four = new JRadioButton("4");
	JRadioButton five = new JRadioButton("5");
	private ButtonGroup buttons;
	static int maxCount;
	public Window(String s) {
		super(s);
		JPanel pane = new JPanel();
		pane.setLayout(new BorderLayout());
		pane.setBorder(new EmptyBorder(20,20,20,20));
		this.add(pane);
		JPanel north = new JPanel();
		north.setLayout(new GridLayout(4,1,10,5));
		north.setBorder(new EmptyBorder(0,0,20,0));
		JPanel textFields = new JPanel();
		textFields.setLayout(new GridLayout(2,2,10,10));
		JLabel fromL = new JLabel("From:");
		JLabel toL = new JLabel("To:");
		textFields.add(fromL);
		textFields.add(toL);
		 from = new JTextField();
		 to = new JTextField();
		textFields.add(from);
		textFields.add(to);
		north.add(textFields);
		buttons = new ButtonGroup();
		buttons.add(two);
		buttons.add(three);
		buttons.add(four);
		buttons.add(five);
		JPanel Buttons = new JPanel();
		Buttons.add(two);
		Buttons.add(three);
		Buttons.add(four);
		Buttons.add(five);
		Buttons.setLayout(new GridLayout(1,4,0,0));
		north.add(new JLabel("Set Maximum number of hops:"));
		north.add(Buttons);
		 go = new JButton("Go");
		go.addActionListener(this);
		north.add(go);
		pane.add(north,BorderLayout.NORTH);
		 output = new JTextArea();
		output.setEditable(false);
		pane.add(output, BorderLayout.CENTER);
	}
	public static void main (String [] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
		}
		Window window = new Window("Degrees of Wikipedia");
		window.setBounds(100,100,600,600);
		window.setVisible(true);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void actionPerformed(ActionEvent arg0) {
		Object o = buttons.getSelection();
		if (o==two)
			maxCount=-1;
		if(o==three)
			maxCount=0;
		if(o==four)
			maxCount=1;
		if(o==five)
			maxCount=2;
		Crawler obj = new Crawler(from.getText(), to.getText());
		try {
			obj.getLinks();
			int min = 0;
			for (int i = 0; i < Crawler.pathLengths.size(); i++) {
				if (Crawler.pathLengths.get(i) < Crawler.pathLengths.get(min))
					min = i;
			}
			output.setText("Degree of Seperation: " + Crawler.pathLengths.get(min) + '\n' + Crawler.paths.get(min));
		} catch (Exception e) {
			output.setText("Could not find path.");
		}	
	}
}
