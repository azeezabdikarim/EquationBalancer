package matrix;


import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * This program demonstrates how to use JPanel in Swing.
 * @author www.codejava.net
 */
public class EquationBalance {


	private JLabel leftCushion = new JLabel("  ");
	private JLabel arrow = new JLabel(" ---> ");
	private JTextField leftEq = new JTextField(20);
	private JTextField rightEq = new JTextField(20);
	private JButton submit = new JButton("Submit");

	public void balance() {
		JFrame frame = new JFrame("Equation Balancer");
		JPanel panel = new JPanel();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 100);

		frame.add(panel);
		JButton button = new JButton("Submit");
		button.addActionListener(new Action());
		panel.add(leftCushion);
		panel.add(leftEq);
		panel.add(arrow);
		panel.add(rightEq);
		panel.add(button);
		JLabel instructions = new JLabel("Type in Chemical Equation. Insert a space between each compound.");
		JLabel instructions2 = new JLabel("Result will appear in the Console");
		instructions.locate(200, 70);
		instructions2.locate(400, 85);
		panel.add(instructions);
		panel.add(instructions2);
	}


	class Action implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//			String ls = leftEq.getText();
			//			String rs = rightEq.getText();
			//			String ls = "2C2H6 + 7O2";
			//			String rs = "4CO2 + 6H2O";
			String ls = "C3H8 + O2";
			String rs = "CO2 + H2O";
			CharTest cTest = new CharTest();
			Matrix matrix = new Matrix(cTest.makeArray(ls,rs));
			//matrix.print();
			Matrix ret = matrix.toRREF(matrix.toArray());
			//ret.print();
			double[][] d = ret.toArray();
			double[][] practice = {{1, -4}, {3, 2}, {0, 5}};
			double[] coefficients = new double[ret.columns];
			coefficients[ret.columns-1] = 1.0;
			for(int x = 0; x < ret.rows; x++){
				coefficients[x] = Math.abs(ret.matrix[x][ret.columns-1]);
			}
			for(int x = 0; x < coefficients.length; x++){
				for(int i = 0; i < coefficients.length; i++){
					if(((int)coefficients[x]/1) != coefficients[x]){
						double scale = (1.0)/coefficients[x];

						for(int z = 0; z < coefficients.length; z++){
							coefficients[z] = coefficients[z]*scale;
						}
					}
				}
			}
			int[] intCoefficients = new int[coefficients.length];
			for(int x = 0; x < intCoefficients.length; x++){
				intCoefficients[x] = (int)coefficients[x];
			}
			List compounds = (List) cTest.listCompoundsWithArrow(ls, rs);
			int arrowPosition = 0;
			String equation = new String();
			for(int x = 0; x < compounds.size(); x++){
				String s = "arrow";
				if(compounds.get(x).equals(s)){
					arrowPosition = x;
					equation += " -->";
					x++;
				}
				if(x == 0){
					equation += intCoefficients[x]+""+compounds.get(x);
				}
				if(arrowPosition == 0){		
					if((x != 0) && !(compounds.get(x-1).equals(s))){
						equation += " + "+intCoefficients[x]+""+compounds.get(x);
					}
				}
				if(arrowPosition != 0){
					if((x != 0) && !(compounds.get(x-1).equals(s))){
						equation += " + "+intCoefficients[x-1]+""+compounds.get(x);
					}	
					if(x == (arrowPosition+1)){
						equation += " "+intCoefficients[x-1]+""+compounds.get(x);
					}
						
						
				}
			}
			//System.out.println(Arrays.toString(intCoefficients));
			System.out.println("Result: "+equation);			
		}
	}

	//C2H2Cl4 + CaOH2
	public static void main(String[] args) {
		// set look and feel to the system look and feel


		EquationBalance e = new EquationBalance();
		e.balance();
	}
}