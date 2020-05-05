package function;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

/**
 * ʵ�ִ��ļ���ť����ͼƬ�ļ������
 * 
 * @author 360��˳��
 *
 * @date 2020/05/01
 */
public class FileSend {

	String userName;
	String path;
	String fileName;
	JTextPane textShowPane;
	JTextPane textInputPane;

	public FileSend() {};
	
	public FileSend(String userName, JTextPane textShowPane, JTextPane textInputPane) {

		this.userName = userName;
		this.textShowPane = textShowPane;
		this.textInputPane = textInputPane;
	}

	
	// ����ѡ����жϷ��͵����ļ�����ͼƬ
	public void send() {
		// ����ļ���ť���Դ��ļ�ѡ���
		JFileChooser fileChooser = new JFileChooser();

		int result = fileChooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {

			// ��ѡ���ļ�·��
			path = fileChooser.getSelectedFile().getAbsolutePath();
			// ��ѡ���ļ�����
			fileName = fileChooser.getSelectedFile().getName();

			// ѡ�����ͼƬ
			if (path.endsWith(".png") || path.endsWith(".jpg") || path.endsWith(".gif") || path.endsWith(".jpeg")) {
				sendImage(path, fileName);
			} else {
				sendFile(path, fileName);
			}

		}
		
		
	}

	
	// ����ͼƬ
	public void sendImage(String path, String fileName) {

		// ��ȡͼƬ
		ImageIcon imageIcon = new ImageIcon(path);

		// ���ͼƬ������������������С
		int width, height;
		if (imageIcon.getIconWidth() > 950 || imageIcon.getIconHeight() > 400) {
			width = 600;
			height = 250;
		} else {
			width = imageIcon.getIconWidth();
			height = imageIcon.getIconHeight();
		}

		// ����ͼƬ��С
		imageIcon.setImage(imageIcon.getImage().getScaledInstance(width, height, 0));

		// ��ȡ����
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM--dd HH:mm:ss");

		String input = dateFormat.format(date) + "\n";

		// ΪͼƬ������Ӱ�ť�����ڴ�ͼƬ
		JButton button = new JButton(fileName);
		button.setFont(new Font("����", Font.PLAIN, 20));
		button.setBackground(Color.WHITE);
		button.setBorderPainted(false);
		button.setFocusPainted(false);

		// ��ȡ����չʾ�������ݣ�����ͼƬ�ļ��Ĳ���
		Document document = textShowPane.getDocument();
		try {
			// ��������
			document.insertString(document.getLength(), input, new SimpleAttributeSet());

			// ����ͼƬ
			textShowPane.insertIcon(imageIcon);

			// ����
			document.insertString(document.getLength(), "\n", new SimpleAttributeSet());

			// ���밴ť��Ҳ����ͼƬ����
			textShowPane.insertComponent(button);

			document.insertString(document.getLength(), "\n\n", new SimpleAttributeSet());

		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Ϊ��ť��ӵ���¼�
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {

					// ʵ�ִ��ļ�����
					File file = new File(path);
					Desktop.getDesktop().open(file);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});

		// ������������
		textInputPane.setText("");

		// ����·������Ӧ���˺���Ϣ��
		String saveText = input + path + "\n\n";
		SaveRecords records = new SaveRecords(userName, saveText);
		records.saveRecords();
	}
	

	// �����ļ�
	public void sendFile(String path, String fileName) {
		// ��ȡ�̶��ļ�ͼ��
		Icon fileImage = new ImageIcon("src/pictures/document.png");

		// ��ȡ����
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String input = dateFormat.format(date) + "\n";

		// Ϊ������Ӱ�ť
		JButton button = new JButton(fileName);
		button.setFont(new Font("����", Font.PLAIN, 20));
		button.setBackground(Color.WHITE);
		button.setBorderPainted(false);
		button.setFocusPainted(false);

		// ��ȡ�������
		Document document = textShowPane.getDocument();

		try {
			document.insertString(document.getLength(), input, new SimpleAttributeSet());

			textShowPane.insertIcon(fileImage);

			textShowPane.insertComponent(button);

			document.insertString(document.getLength(), "\n\n", new SimpleAttributeSet());
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Ϊ���ư�ť��Ӽ����¼���ʵ�ִ򿪹���
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					// ʵ�ִ��ļ�����
					File file = new File(path);
					Desktop.getDesktop().open(file);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});

		textInputPane.setText("");

		// ����·������Ӧ���˺���Ϣ��
		String saveText = input + path + "\n\n";
		SaveRecords records = new SaveRecords(userName, saveText);
		records.saveRecords();
	}
	
	//���ͱ��������
	public void sendEmoji(String path) {

		// ��ȡͼƬ
		ImageIcon imageIcon = new ImageIcon(path);


		// ��ȡ����
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM--dd HH:mm:ss");

		String input = dateFormat.format(date) + "\n";

		// ��ȡ����չʾ�������ݣ�����ͼƬ�ļ��Ĳ���
		Document document = textShowPane.getDocument();
		try {
			// ��������
			document.insertString(document.getLength(), input, new SimpleAttributeSet());

			// ����ͼƬ
			textShowPane.insertIcon(imageIcon);


			document.insertString(document.getLength(), "\n\n", new SimpleAttributeSet());

		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// ������������
		textInputPane.setText("");

	}
	

}
