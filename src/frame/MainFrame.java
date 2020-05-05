package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

import function.DropTargetFile;
import function.FileSend;
import function.RecordsEcho;
import function.TextSend;

/**
 * �ļ���������������
 * 
 * @author 360��˳��
 * 
 * @date:2020/04/29 ~ 2020/04/30
 *
 */

public class MainFrame {

	String userName;

	public MainFrame() {
	};

	public MainFrame(String userName) {
		this.userName = userName;
	}

	private JButton fileButton;
	private JButton historicRecordsButton;
	private JButton sendButton;
	private JTextPane showPane;
	private JTextPane inputPane;
	private JButton expressionButton;
	private JScrollPane scrollShowPane;
	private Box buttonBox;
	private Box inputBox;
	private Box sendBox;
	private Box totalBox;
	private ImageIcon image;
	static JFrame mainFrame;

	public void init() {

		// ��ʾ�ı�����
		showPane = new JTextPane();
		showPane.setSize(600, 400);
		showPane.setBackground(Color.WHITE);
		showPane.setEditable(false);
		showPane.setBorder(null);
		showPane.setFont(new Font("����", 0, 25));
		// ��ʾ�ı�������ӹ�����
		scrollShowPane = new JScrollPane(showPane);

		// ��������o�����ͼ��
		Icon expressionIcon = new ImageIcon("src/pictures/expression.png");
		expressionButton = new JButton(expressionIcon);
		expressionButton.setBackground(Color.WHITE);
		expressionButton.setFocusPainted(false);
		expressionButton.setBorderPainted(false);

		// �ļ���ť�����ͼ��
		Icon fileIcon = new ImageIcon("src/pictures/file.png");
		fileButton = new JButton(fileIcon);
		fileButton.setBackground(Color.WHITE);
		fileButton.setFocusPainted(false);
		fileButton.setBorderPainted(false);

		// ��ʷ��¼��ť�����ͼ��
		Icon historicRecordsIcon = new ImageIcon("src/pictures/historicRecords.png");
		historicRecordsButton = new JButton(historicRecordsIcon);
		historicRecordsButton.setBackground(Color.WHITE);
		historicRecordsButton.setFocusPainted(false);
		historicRecordsButton.setBorderPainted(false);

		// ��ťBox�������������ť
		buttonBox = Box.createHorizontalBox();
		buttonBox.setPreferredSize(new Dimension(1000, 50));
		buttonBox.add(Box.createHorizontalStrut(10));
		buttonBox.add(expressionButton);
		buttonBox.add(Box.createHorizontalStrut(10));
		buttonBox.add(fileButton);
		buttonBox.add(Box.createHorizontalStrut(10));
		buttonBox.add(historicRecordsButton);
		// ��� ����ʷ��¼����ť���ұ߿�ľ��� ��buttonBox������
		buttonBox.add(Box.createHorizontalGlue());

		// �����ı�����
		inputPane = new JTextPane();
		inputPane.setSize(600, 300);
		inputPane.setFont(new Font("����", 0, 24));
		inputPane.setBackground(Color.WHITE);
		JScrollPane scrollInputPane = new JScrollPane(inputPane);

		// ���������Box����
		inputBox = Box.createHorizontalBox();
		inputBox.setPreferredSize(new Dimension(1000, 150));
		inputBox.add(scrollInputPane);

		// ���Ͱ�ť
		sendButton = new JButton("����(S)");
		sendButton.setFont(new Font("�п�", Font.PLAIN, 20));
		sendButton.setBackground(Color.WHITE);
		sendButton.setFocusPainted(false);
		sendButton.setBorderPainted(false);

		// ����Box��������ӷ��Ͱ�ť
		sendBox = Box.createHorizontalBox();
		sendBox.setPreferredSize(new Dimension(1000, 50));
		sendBox.setBackground(Color.white);
		sendBox.add(Box.createHorizontalStrut(710));
		sendBox.add(Box.createVerticalStrut(5));
		sendBox.add(sendButton);
		sendBox.add(Box.createVerticalStrut(5));

		// �ܵ�Box�����������3��Box
		totalBox = Box.createVerticalBox();
		totalBox.setPreferredSize(new Dimension(1000, 250));
		totalBox.setSize(1000, 400);
		totalBox.add(buttonBox);
		totalBox.add(inputBox);
		totalBox.add(Box.createVerticalStrut(3));
		totalBox.add(sendBox);
		totalBox.add(Box.createVerticalStrut(3));

		// ����������
		mainFrame = new JFrame("�ļ���������");
		mainFrame.setSize(950, 800);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// �ı䴰��logo
		image = new ImageIcon("src/pictures/logo.png");
		mainFrame.setIconImage(image.getImage());
		mainFrame.setLayout(new BorderLayout());
		// ��Ӵ�������������Ҫ����
		mainFrame.add(scrollShowPane, BorderLayout.CENTER);
		mainFrame.add(totalBox, BorderLayout.SOUTH);
		mainFrame.setVisible(true);

		// ��Ӽ�����
		addListen();

		// ��Ϣ��¼���Ե�չʾ���
		RecordsEcho echo = new RecordsEcho(userName, showPane);
		echo.read();

	}

	// ��ʾ�Ի���
	public static void warnJDialog(String information) {
		JDialog jDialog = new JDialog(mainFrame, "��ʾ");
		jDialog.setLayout(null);
		jDialog.setSize(300, 200);
		jDialog.setLocation(770, 400);
		ImageIcon image = new ImageIcon("src/pictures/warn.png");
		jDialog.setIconImage(image.getImage());

		JLabel jLabel = new JLabel(information);
		jLabel.setFont(new Font("�п�", 0, 21));
		jLabel.setBounds(48, 0, 200, 100);
		jDialog.add(jLabel);

		JButton button = new JButton("ȷ��");
		button.setBounds(105, 80, 70, 40);
		button.setFont(new Font("΢���ź�", 1, 18));
		button.setBackground(Color.WHITE);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		jDialog.add(button);

		// Ϊ�����Ի���ť��Ӽ����¼�
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jDialog.dispose();
			}
		});

		jDialog.setVisible(true);
	}

	// ��Ӽ����¼�
	@SuppressWarnings("unused")
	public void addListen() {

		/*
		 * Ϊ�����ı����Ŀ�������
		 */

		// ������קĿ�������
		DropTargetListener listener = new DropTargetFile(inputPane);
		// �� inputPane��ע����קĿ�������
		DropTarget dropTarget = new DropTarget(inputPane, DnDConstants.ACTION_COPY_OR_MOVE, listener, true);

		// ���Ͱ�ť�����¼�
		sendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				TextSend textSend = new TextSend(showPane, inputPane, userName);
				textSend.sendText();
			}
		});

		// �������Ӽ����¼�
		inputPane.addKeyListener(new KeyListener() {

			// ���������¼�ʱ������
			@Override
			public void keyTyped(KeyEvent e) {

			}

			// �������ͷ�ʱ������
			@Override
			public void keyReleased(KeyEvent e) {

			}

			// ����������ʱ������
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

				// ������µ��� Ctrl + Enter ��ϼ� ����
				if ((e.getKeyCode() == KeyEvent.VK_ENTER) && e.isControlDown()) {

					Document document = inputPane.getDocument();

					try {
						document.insertString(document.getLength(), "\n", new SimpleAttributeSet());
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// ������
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					TextSend textSend = new TextSend(showPane, inputPane, userName);
					textSend.sendText();

				}

			}

		});

		// �������ť�����¼�
		expressionButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new EmojiFrame(showPane, userName).init();
			}
		});

		// �ļ���ť�����¼�
		fileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FileSend fileSend = new FileSend(userName, showPane, inputPane);
				fileSend.send();
			}
		});

		// ��ʷ��¼��ť�����¼�
		historicRecordsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new HistoricRecordsFrame(userName, showPane).init();
			}
		});
	}

}
