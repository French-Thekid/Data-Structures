/*Lomar Lilly 1401375
 * Darryl Brown 1503803
 */
package gui;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import classes.LinkedList;

public class fileChooser {
	
	private static String filePath;
	
	public static String getFilePath(){
		return filePath;
	}
	
	public void setFilePath(String filePath){
		fileChooser.filePath = filePath;
	}
	
	public String openFile(String DataStructure){
		JButton open = new JButton();
		JFileChooser fc = new JFileChooser();
		try
		{
			fc.setCurrentDirectory(new java.io.File("user.home"));
			fc.setDialogTitle("Import Dictionary Data for "+DataStructure);
			fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			if(fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION){
				System.out.println("file loaded");
			}
			this.setFilePath(fc.getSelectedFile().getAbsolutePath());
		}
		catch(Exception err)
		{
			System.out.println(err);
		}
		return fc.getSelectedFile().getAbsolutePath();
	}
}
