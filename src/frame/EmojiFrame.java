package frame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

import function.SaveRecords;



/**
 * ����ʵ�ֱ��������
 * 
 * @author 360��˳��
 * 
 * @date 2020/05/03
 *
 */
public class EmojiFrame {

	//չʾ���
	private JTextPane showPane;
	
	//�������ť
	private JButton[] buttons = new JButton[55];

	//�����ͼƬ
	private ImageIcon[] icons = new ImageIcon[55];

	//������Ի���
	private JDialog emojiJDialog;
	
	//�˺�
	private String userName;

	public EmojiFrame(JTextPane showPane,String userName) {
		this.showPane = showPane;
		this.userName = userName;
	}
	
	//���������
	public void init() {

		//�öԻ�����װ�����
		emojiJDialog = new JDialog();
		emojiJDialog.setTitle("�����");
		emojiJDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		emojiJDialog.setLayout(new GridLayout(6, 9));
		emojiJDialog.setBounds(490, 263, 500, 400);
		emojiJDialog.setBackground(Color.WHITE);
		ImageIcon image = new ImageIcon("src/pictures/emoji.png");
		emojiJDialog.setIconImage(image.getImage());

		//������ð�ť��ʵ�֣���Ҫ�ǿ�����Ӽ����¼�����������ʵ�ַ���
		for (int i = 1; i <= 54; i++) {

			String path = "src/pictures/emoji_" + i + ".png";
			icons[i] = new ImageIcon(path);
			buttons[i] = new JButton(icons[i]);
			buttons[i].setBackground(Color.WHITE);
			buttons[i].setBorder(null);
			buttons[i].setBorderPainted(false);
			buttons[i].setSize(20, 20);
			buttons[i].setFocusPainted(false);

			emojiJDialog.add(buttons[i]);

		}
		
		emojiJDialog.setVisible(true);
		
		//��Ӽ����¼�
		addListen();

	}
	
	
	//�����¼�
	public void addListen() {
		
		//Ϊÿһ����ť��Ӽ����¼�
		for(int i=1;i<=54;i++) {
			
			String path = "src/pictures/emoji_" + i + ".png";
			
			buttons[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					//��ȡͼƬ
					ImageIcon imageIcon = new ImageIcon(path);			
					
					// ��ȡ����
					Date date = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

					String input = dateFormat.format(date) + "\n";
					
					Document document  = showPane.getDocument();
					
					try {
						//д������
						document.insertString(document.getLength(), input, new SimpleAttributeSet());
						
						//����ͼƬ
						showPane.insertIcon(imageIcon);
						
						//����
						document.insertString(document.getLength(), "\n\n", new SimpleAttributeSet());
						
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					// ����·������Ӧ���˺���Ϣ���Ϊ�õ��Ǿ���·��������Ҫ����ʵ�����޸�
					String saveText = input + "D:\\eclipse jee\\FileTransfer\\" + path + "\n\n";
					SaveRecords records = new SaveRecords(userName, saveText);
					records.saveRecords();
					
					
					emojiJDialog.setVisible(false);
				}
			});
		}
	}
}
