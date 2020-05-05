package function;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

/**
 * 
 * ����ʵ����ʷ��¼����
 * 
 * @author 360��˳��
 * 
 * @date 2020/05/02
 *
 */
public class RecordsEcho {

	private String userName;
	private JTextPane showPane;

	public RecordsEcho(String userName, JTextPane showPane) {
		this.userName = userName;
		this.showPane = showPane;
	}

	// ���û�����Ϣ��¼���Ե�չʾ���
	public void read() {

		// ��Ӧ�˺ŵ���Ϣ��¼�ı�
		File file = new File("src/txt/" + userName + ".txt");

		// �ļ������ھʹ���һ��
		if (!file.exists()) {

			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {

			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);

			// ������ʽ
			Pattern pattern = Pattern.compile(".+[\\.].+");

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {

				Matcher matcher = pattern.matcher(str);

				// ������ļ���ͼƬ
				if (matcher.find()) {

					// ����ļ���
					int index = str.lastIndexOf("\\");
					String fileName = str.substring(index + 1);

					// ͼƬ�����
					if (str.endsWith(".png") || str.endsWith(".jpg") || str.endsWith(".jpeg") || str.endsWith("gif")) {

						Pattern pattern1 = Pattern.compile("[emoji_].+[\\.].+");

						Matcher matcher1 = pattern1.matcher(fileName);

						// ����Ǳ����
						if (matcher1.find()) {
							writeEmoji(str);
						} else {
							writeImage(str, fileName);
						}
					} else {
						// �ļ������
						writeFile(str, fileName);
					}

				} else {
					// ������ı���ֱ��д��
					writeText(str);
				}

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// ���ı���ʾ��չʾ���
	public void writeText(String str) {

		String s = "";

		for (int i = 0; i < str.length(); i++) {

			if (i != 0 && i % 30 == 0)
				s += "\n";

			s += str.charAt(i);

		}

		Document document = showPane.getDocument();

		try {
			document.insertString(document.getLength(), s + "\n", new SimpleAttributeSet());
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void writeEmoji(String path) {

		// ��ȡͼƬ
		ImageIcon imageIcon = new ImageIcon(path);

		// ��ȡ����չʾ�������ݣ�����ͼƬ�ļ��Ĳ���
		Document document = showPane.getDocument();

		try {

			// ����ͼƬ
			showPane.insertIcon(imageIcon);

			// ����
			document.insertString(document.getLength(), "\n", new SimpleAttributeSet());

		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ��ͼƬ��ʾ��չʾ���
	public void writeImage(String path, String fileName) {
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


		// ΪͼƬ������Ӱ�ť�����ڴ�ͼƬ
		JButton button = new JButton(fileName);
		button.setFont(new Font("����", Font.PLAIN, 20));
		button.setBackground(Color.WHITE);
		button.setBorderPainted(false);
		button.setFocusPainted(false);

		// ��ȡ����չʾ�������ݣ�����ͼƬ�ļ��Ĳ���
		Document document = showPane.getDocument();
		try {

			// ����ͼƬ
			showPane.insertIcon(imageIcon);

			// ����
			document.insertString(document.getLength(), "\n", new SimpleAttributeSet());

			// ���밴ť��Ҳ����ͼƬ����
			showPane.insertComponent(button);

			document.insertString(document.getLength(), "\n", new SimpleAttributeSet());

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

	}

	// ���ļ���ʾ��չʾ�����
	public void writeFile(String path, String fileName) {

		// ��ȡ�̶��ļ�ͼ��
		Icon fileImage = new ImageIcon("src/pictures/document.png");

		// Ϊ������Ӱ�ť
		JButton button = new JButton(fileName);
		button.setFont(new Font("����", Font.PLAIN, 20));
		button.setBackground(Color.WHITE);
		button.setBorderPainted(false);
		button.setFocusPainted(false);

		// ��ȡ�������
		Document document = showPane.getDocument();

		try {

			showPane.insertIcon(fileImage);

			showPane.insertComponent(button);

			document.insertString(document.getLength(), "\n", new SimpleAttributeSet());
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	}

}
