package frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import function.Login;

/**
 * �ļ��������ֵ�½����
 * 
 * @author��360��˳��
 * 
 * @date��2020/04/27
 * 
 */

public class LoginFrame {

	public static JFrame loginJFrame;
	public static JLabel userNameLabel;
	public static JTextField userNameTextField;
	public static JLabel passwordLabel;
	public static JPasswordField passwordField;
	public static JButton loginButton;
	public static JButton registerButton;

	public static void main(String[] args) {

		// ��������
		loginJFrame = new JFrame("�ļ���������");
		loginJFrame.setSize(500, 300);
		loginJFrame.setLocationRelativeTo(null);
		loginJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon image = new ImageIcon("src/pictures/logo.png");
		loginJFrame.setIconImage(image.getImage());
		loginJFrame.setResizable(false);

		// �����������
		Container container = loginJFrame.getContentPane();
		container.setLayout(null);

		// �������˺š���ǩ
		userNameLabel = new JLabel("�˺ţ�");
		userNameLabel.setFont(new Font("�п�", Font.BOLD, 25));
		userNameLabel.setBounds(60, 25, 100, 100);
		container.add(userNameLabel);

		// ���������˺��ı���
		userNameTextField = new JTextField();
		userNameTextField.setFont(new Font("����", Font.PLAIN, 23));
		userNameTextField.setBounds(133, 61, 280, 33);
		container.add(userNameTextField);

		// ���������롱��ǩ
		passwordLabel = new JLabel("���룺");
		passwordLabel.setFont(new Font("�п�", Font.BOLD, 25));
		passwordLabel.setBounds(60, 90, 100, 100);
		container.add(passwordLabel);

		// �������������ı���
		passwordField = new JPasswordField();
		passwordField.setBounds(133, 127, 280, 33);
		passwordField.setFont(new Font("Arial", Font.BOLD, 23));
		container.add(passwordField);

		// ������¼��ť
		loginButton = new JButton("��¼");
		loginButton.setBounds(170, 185, 70, 40);
		loginButton.setFont(new Font("΢���ź�", 1, 18));
		loginButton.setBackground(Color.WHITE);
		loginButton.setFocusPainted(false);
		loginButton.setBorderPainted(false);

		container.add(loginButton);

		// ����ע�ᰴť
		registerButton = new JButton("ע��");
		registerButton.setBounds(282, 185, 70, 40);
		registerButton.setFont(new Font("΢���ź�", 1, 18));
		registerButton.setBackground(Color.WHITE);
		registerButton.setFocusPainted(false);
		registerButton.setBorderPainted(false);
		container.add(registerButton);

		// ��ʾ����
		loginJFrame.setVisible(true);

		addListen();
	}

	// Ϊ��ť��Ӽ�����
	public static void addListen() {

		// Ϊ��¼��ť��Ӽ����¼�
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// ����Login���󲢰�LoginFrame���ı���������Ϊ��������ȥ
				Login login = new Login(userNameTextField, passwordField);

				// �ж��Ƿ���ϵ�¼�ɹ�������
				if (login.isEmptyUserName()) {
					emptyUserName(loginJFrame);
				} else {
					if (login.isEmptyPassword()) {
						emptyPasswordJDialog(loginJFrame);
					} else {
						if (login.queryInformation()) {
							loginJFrame.dispose();
							MainFrame mainFrame = new MainFrame(userNameTextField.getText());
							mainFrame.init();
						} else {
							failedLoginJDialog(loginJFrame);
						}
					}
				}
			}
		});

		// Ϊע�ᰴť��Ӽ����¼�
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// ���ص�ǰ��¼����
				loginJFrame.setVisible(false);

				// ��ע�ᴰ��
				new RegisterFrame().init();
			}
		});

	}

	/*
	 * ���ڸ�����ǩ���Ȳ�ͬ������Ϊ�˽������ۣ���д�����������Ի��������һ����
	 * 
	 */

	// δ�����˺�ʱ������ʾ�Ի���
	public static void emptyUserName(JFrame jFrame) {
		JDialog jDialog = new JDialog(jFrame, "��ʾ");
		jDialog.setLayout(null);
		jDialog.setSize(300, 200);
		jDialog.setLocationRelativeTo(null);
		ImageIcon image = new ImageIcon("src/pictures/warn.png");
		jDialog.setIconImage(image.getImage());

		JLabel jLabel = new JLabel("δ�����˺ţ�");
		jLabel.setFont(new Font("�п�", 0, 21));
		jLabel.setBounds(82, 0, 200, 100);
		jDialog.add(jLabel);

		JButton button = new JButton("ȷ��");
		button.setBounds(105, 80, 70, 40);
		button.setFont(new Font("΢���ź�", 1, 18));
		button.setBackground(Color.WHITE);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		jDialog.add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jDialog.dispose();
			}
		});

		jDialog.setVisible(true);
	}

	// δ��������ʱ������ʾ�Ի���
	public static void emptyPasswordJDialog(JFrame jFrame) {
		JDialog jDialog = new JDialog(jFrame, "��ʾ");
		jDialog.setLayout(null);
		jDialog.setSize(300, 200);
		jDialog.setLocationRelativeTo(null);
		ImageIcon image = new ImageIcon("src/pictures/warn.png");
		jDialog.setIconImage(image.getImage());

		JLabel jLabel = new JLabel("δ�������룡");
		jLabel.setFont(new Font("�п�", 0, 21));
		jLabel.setBounds(82, 0, 200, 100);
		jDialog.add(jLabel);

		JButton button = new JButton("ȷ��");
		button.setBounds(105, 80, 70, 40);
		button.setFont(new Font("΢���ź�", 1, 18));
		button.setBackground(Color.WHITE);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		jDialog.add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jDialog.dispose();
			}
		});

		jDialog.setVisible(true);
	}

	// �˺Ż������������
	public static void failedLoginJDialog(JFrame jFrame) {

		JDialog jDialog = new JDialog(jFrame, "��ʾ");
		jDialog.setLayout(null);
		jDialog.setSize(300, 200);
		jDialog.setLocationRelativeTo(null);
		ImageIcon image = new ImageIcon("src/pictures/warn.png");
		jDialog.setIconImage(image.getImage());

		JLabel jLabel = new JLabel("�˺Ż������������");
		jLabel.setFont(new Font("�п�", 0, 20));
		jLabel.setBounds(47, 0, 200, 100);
		jDialog.add(jLabel);

		JButton button = new JButton("ȷ��");
		button.setBounds(105, 80, 70, 40);
		button.setFont(new Font("΢���ź�", 1, 18));
		button.setBackground(Color.WHITE);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		jDialog.add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jDialog.dispose();
			}
		});

		jDialog.setVisible(true);

	}
}
