package org.GUICoder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

class Frame extends JFrame{
	JPanel p=new JPanel(new BorderLayout());
	JLabel title=new JLabel("GUICoder v1.0");
	JTextArea ta=new JTextArea("enter your text here");
	JTextArea result=new JTextArea("result");
	JButton b1=new JButton("translate");
	JButton b2=new JButton("decode");
	JComboBox list=new JComboBox();
	JFileChooser chooser=new JFileChooser();
	JButton open=new JButton("open file...");
	JButton save=new JButton("save result to file...");
	JSpinner lvl=new JSpinner();
	JButton exit=new JButton("exit");
	private Coder coder=null;

	private class ExitListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			System.exit(0);
		}
	}

	private class DecodeListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			result.setText(coder.decode(ta.getText(), (Integer)lvl.getValue()));
		}
	}

	private class SaveListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int val=chooser.showOpenDialog(new JFrame());
			if(val==JFileChooser.APPROVE_OPTION)
				save(result.getText(), chooser.getSelectedFile());
		}
	}

	private class OpenListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			int val=chooser.showOpenDialog(new JFrame());
			if(val==JFileChooser.APPROVE_OPTION){
				try{
					ta.setText(load(chooser.getSelectedFile()));
				} catch(FileNotFoundException e){
					JOptionPane.showMessageDialog(null, "FILE NOT FOUND: "+e, "FileNotFoundException", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	private class B1Listener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			result.setText(coder.code(ta.getText(), (Integer)lvl.getValue()));
		}
	}

	@SuppressWarnings("unchecked")
	private class ListListener implements ActionListener{
		public void actionPerformed(ActionEvent ev){
			coder=(Coder)list.getSelectedItem();
			if(coder instanceof Cezar)
				try{
					coder.setParametr(Integer.parseInt(JOptionPane.showInputDialog(null, "Move in alphabet:", "GUICoder: Cezar setting", JOptionPane.PLAIN_MESSAGE)));
				} catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Number required", "ERROR", JOptionPane.ERROR_MESSAGE);
					actionPerformed(ev);
				}
			else if(coder instanceof Vigenere){
				String tmp=JOptionPane.showInputDialog(null, "Enter key:", "GUICoder: Vigenere", JOptionPane.PLAIN_MESSAGE);
				if(tmp==null||tmp.equals("")){
					JOptionPane.showMessageDialog(null, "Enter key!", "ERROR", JOptionPane.ERROR_MESSAGE);
					actionPerformed(ev);
				} else
					coder.setParametr(tmp);
			}
			repaint();
		}
	}

	{
		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);
		b1.addActionListener(new B1Listener());
		b2.addActionListener(new DecodeListener());
		list.addActionListener(new ListListener());
		list.addItem(new Atbash());
		list.addItem(new Cezar());
		list.addItem(new Vigenere());
		list.addItem(new Leet());
		result.setEditable(false);
		result.setOpaque(true);
		result.setBackground(Color.LIGHT_GRAY);
		result.setLineWrap(true);
		result.setWrapStyleWord(true);
		chooser.setFileFilter(new FileNameExtensionFilter("only text", "txt", "java", "cpp"));
		open.addActionListener(new OpenListener());
		save.addActionListener(new SaveListener());
		exit.addActionListener(new ExitListener());
		lvl.setValue(1);
		coder=new Cezar(2);
	}

	Frame(){
		super("GUICODER v1.0");
		setLocation(100, 100);
		setSize(800,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel north=new JPanel();
		north.add(title);
		north.add(list);
		north.add(lvl);
		north.add(open);
		north.add(save);
		north.add(exit);
		p.add(north, BorderLayout.NORTH);
		list.setPreferredSize(new Dimension(200,25));
		p.add(ta, BorderLayout.CENTER);
		ta.setPreferredSize(new Dimension(400,300));
		p.add(result, BorderLayout.EAST);
		result.setPreferredSize(new Dimension(400,300));
		JPanel south=new JPanel();
		south.setLayout(new GridLayout(0,1));
		south.add(b1);
		b1.setPreferredSize(new Dimension(300,50));
		south.add(b2);
		b2.setPreferredSize(new Dimension(300,50));
		p.add(south, BorderLayout.SOUTH);
		south.setPreferredSize(new Dimension(300,100));
		Container c=getContentPane();
		c.add(p);
		setVisible(true);
	}

	private void save(String txt, File dest){
		try{
			if(!dest.exists())
				dest.createNewFile();
			Scanner sc=new Scanner(txt);
			PrintWriter pr=new PrintWriter(dest);
			while(sc.hasNextLine())
				pr.println(sc.nextLine());
			pr.close();
			sc.close();
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	private String load(File f)throws FileNotFoundException{
		Scanner sc=new Scanner(f);
		StringBuilder result=new StringBuilder();
		while(sc.hasNext()){
			result.append(sc.nextLine());
			result.append("\n");
		}
		sc.close();
		return result.toString();
	}
}
