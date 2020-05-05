package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import function.ClearRecords;
import function.FindRecords;
import function.RecordsEcho;



/**
 * ����ʵ����ʷ��¼����
 * 
 * @author 360��˳��
 *
 * @date 2020/05/02
 *
 */
public class HistoricRecordsFrame {

	private String userName;
	private JTextPane textShowPane;
	
	private JFrame historicRecordsFrame;
	private JButton findRecordsButton;
	private JButton clearRecordsButton;
	private JTextField searchTextField;
	private JTextPane recordsShowPane;
	private Box clearBox;

	public HistoricRecordsFrame(String userName,JTextPane textShowPane) {
		this.userName = userName;
		this.textShowPane = textShowPane;
	}
	
	
	public void init() {

		// �����ı���
		searchTextField = new JTextField();
		searchTextField.setFont(new Font("����", 0, 25));

		// ���Ҽ�¼��ť
		findRecordsButton = new JButton("���Ҽ�¼");
		findRecordsButton.setFont(new Font("�п�", Font.PLAIN, 20));
		findRecordsButton.setBackground(Color.WHITE);
		findRecordsButton.setBorderPainted(false);
		findRecordsButton.setFocusPainted(false);

		// �������ı����������ť����Box����
		Box searchBox = Box.createHorizontalBox();
		searchBox.setPreferredSize(new Dimension(900, 47));
		searchBox.setBackground(Color.white);
		searchBox.add(Box.createHorizontalStrut(35));
		searchBox.add(searchTextField);
		searchBox.add(Box.createHorizontalStrut(20));
		searchBox.add(findRecordsButton);
		searchBox.add(Box.createHorizontalStrut(25));

		// ��ʾ�ı�����
		recordsShowPane = new JTextPane();
		recordsShowPane.setSize(900, 600);
		recordsShowPane.setBackground(Color.WHITE);
		recordsShowPane.setEditable(false);
		recordsShowPane.setBorder(null);
		recordsShowPane.setFont(new Font("����", 0, 25));
		// ��ʾ�ı�������ӹ�����
		JScrollPane scrollShowPane = new JScrollPane(recordsShowPane);

		// ��ռ�¼��ť
		clearRecordsButton = new JButton("��ռ�¼");
		clearRecordsButton.setFont(new Font("�п�", Font.PLAIN, 20));
		clearRecordsButton.setBackground(Color.WHITE);
		clearRecordsButton.setBorderPainted(false);
		clearRecordsButton.setFocusable(false);

		// Box�����������ռ�¼��ť
		clearBox = Box.createHorizontalBox();
		clearBox.setPreferredSize(new Dimension(1000, 60));
		clearBox.setBackground(Color.white);
		clearBox.add(Box.createVerticalStrut(5));
		clearBox.add(clearRecordsButton);
		clearBox.add(Box.createVerticalStrut(5));

		// ����������
		historicRecordsFrame = new JFrame("��ʷ��¼");
		historicRecordsFrame.setSize(900, 700);
		historicRecordsFrame.setLocationRelativeTo(null);
		historicRecordsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// �ı䴰��logo
		ImageIcon image = new ImageIcon("src/pictures/historicRecordsLogo.png");
		historicRecordsFrame.setIconImage(image.getImage());
		historicRecordsFrame.setLayout(new BorderLayout());
		// ��Ӵ�������������Ҫ����
		historicRecordsFrame.add(searchBox, BorderLayout.NORTH);
		historicRecordsFrame.add(scrollShowPane, BorderLayout.CENTER);
		historicRecordsFrame.add(clearBox, BorderLayout.SOUTH);
		historicRecordsFrame.setVisible(true);
		
		addListen();
		
		RecordsEcho recordsEcho = new RecordsEcho(userName, recordsShowPane);
		recordsEcho.read();
		
		
		
	}
	
	
	//��Ӱ�ť�����¼�
	public void addListen() {
		
		//�����ʷ��¼�����¼�
		clearRecordsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ClearRecords(userName,textShowPane,recordsShowPane).clear();
			}
		});
		
		//���Ҽ�¼�����¼�
		findRecordsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FindRecords findRecords = new FindRecords(recordsShowPane,userName,searchTextField.getText());
				findRecords.find();
			}
		});
	}
}
