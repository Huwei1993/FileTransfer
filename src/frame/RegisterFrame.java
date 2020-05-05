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

import function.Register;

/**
 *
 * �ļ���������ע�����
 * 
 * @author 360��˳��
 * 
 * @date: 2020/04/27 ~ 2020/04/28
 *
 */
public class RegisterFrame {

	public JFrame registerJFrame;
	public JLabel userNameLabel;
	public JTextField userNameTextField;
	public JLabel passwordLabel;
	public JPasswordField passwordField;
	public JLabel passwordAgainLabel;
	public JPasswordField passwordAgainField;
	public JButton goBackButton;
	public JButton registerButton;


	public void init() {

		// ��������
		registerJFrame = new JFrame("�ļ���������");
//		registerJFrame.setTitle();
		registerJFrame.setSize(540, 400);
		registerJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ImageIcon image = new ImageIcon("src/pictures/logo.png");
		registerJFrame.setIconImage(image.getImage());
		registerJFrame.setLocationRelativeTo(null);
		registerJFrame.setResizable(false);

		// �����������
		Container container = registerJFrame.getContentPane();
		container.setLayout(null);

		// �������˺š���ǩ
		userNameLabel = new JLabel("�˺ţ�");
		userNameLabel.setFont(new Font("�п�", Font.BOLD, 25));
		userNameLabel.setBounds(97, 25, 100, 100);
		container.add(userNameLabel);

		// ���������˺��ı���
		userNameTextField = new JTextField();
		userNameTextField.setFont(new Font("����", Font.PLAIN, 23));
		userNameTextField.setBounds(170, 61, 280, 33);
		container.add(userNameTextField);

		// ���������롱��ǩ
		passwordLabel = new JLabel("���룺");
		passwordLabel.setFont(new Font("�п�", Font.BOLD, 25));
		passwordLabel.setBounds(97, 90, 100, 100);
		container.add(passwordLabel);

		// �������������ı���
		passwordField = new JPasswordField();
		passwordField.setBounds(170, 125, 280, 33);
		passwordField.setFont(new Font("Arial", Font.BOLD, 23));
		container.add(passwordField);

		// ������ȷ�����롱��ǩ
		passwordAgainLabel = new JLabel("ȷ�����룺");
		passwordAgainLabel.setFont(new Font("�п�", Font.BOLD, 25));
		passwordAgainLabel.setBounds(45, 150, 130, 100);
		container.add(passwordAgainLabel);

		// ����ȷ�������ı���
		passwordAgainField = new JPasswordField();
		passwordAgainField.setBounds(170, 185, 280, 33);
		passwordAgainField.setFont(new Font("Arial", Font.BOLD, 23));
		container.add(passwordAgainField);

		// �������ذ�ť
		goBackButton = new JButton("����");
		goBackButton.setBounds(200, 260, 70, 40);
		goBackButton.setFont(new Font("΢���ź�", 1, 18));
		goBackButton.setBackground(Color.WHITE);
		goBackButton.setFocusPainted(false);
		goBackButton.setBorderPainted(false);
		container.add(goBackButton);

		// ����ע�ᰴť
		registerButton = new JButton("ע��");
		registerButton.setBounds(330, 260, 70, 40);
		registerButton.setFont(new Font("΢���ź�", 1, 18));
		registerButton.setBackground(Color.WHITE);
		registerButton.setFocusPainted(false);
		registerButton.setBorderPainted(false);
		container.add(registerButton);

		// ��ʾ����
		registerJFrame.setVisible(true);

		addListen();

	}

	// Ϊ��ť��Ӽ����¼�
	public void addListen() {

		// Ϊע�ᰴť��Ӽ����¼�
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// ����register����ͬʱ��RegisterFrame���ı���������Ϊ��������ȥ
				Register register = new Register(userNameTextField, passwordField, passwordAgainField);

				// �ж������˺��Ƿ�Ϊ��
				if (register.isEmptyUserName()) {

					emptyUserName(registerJFrame);

				} else {

					// �ж����������Ƿ�Ϊ��
					if (register.isEmptyPassword()) {
						emptyPasswordJDialog(registerJFrame);
					}

					else {
						// �ж������ȷ�������Ƿ�һ��
						if (register.isSamePassWord()) {

							// �ж��˺��Ƿ��Ѵ���
							if (!register.isExistAccount()) {

								// ע��ɹ�������

								register.saveInformation();
								registerJFrame.dispose();
								userNameTextField.setText("");
								passwordField.setText("");
								passwordAgainField.setText("");

								new LoginFrame();
								LoginFrame.loginJFrame.setVisible(true);

								successRegisterJDialog(registerJFrame);

							} else
								existAccountJDialog(registerJFrame);
						} else {
							differentPasswordJDialog(registerJFrame);
							passwordField.setText("");
							passwordAgainField.setText("");
						}
					}
				}

			}
		});

		// Ϊ���ذ�ť��Ӽ����¼�
		goBackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ����ע�ᴰ��
				registerJFrame.dispose();

				// ������ʾ��¼����
				new LoginFrame();
				LoginFrame.loginJFrame.setVisible(true);
			}
		});

	}

	/*
	 * ���ڸ�����ǩ���Ȳ�ͬ������Ϊ�˽������ۣ���д�����������Ի��������һ����
	 * 
	 */

	// δ�����˺�ʱ������ʾ�Ի���
	public void emptyUserName(JFrame jFrame) {
		JDialog jDialog = new JDialog(jFrame, "��ʾ");
		jDialog.setLayout(null);
		jDialog.setSize(300, 200);
		jDialog.setLocationRelativeTo(null);
		ImageIcon image = new ImageIcon("src/pictures/warn.png");
		jDialog.setIconImage(image.getImage());

		JLabel jLabel = new JLabel("δ�����û�����");
		jLabel.setFont(new Font("�п�", 0, 21));
		jLabel.setBounds(73, 0, 200, 100);
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
	public void emptyPasswordJDialog(JFrame jFrame) {
		JDialog jDialog = new JDialog(jFrame, "��ʾ");
		jDialog.setLayout(null);
		jDialog.setSize(300, 200);
		jDialog.setLocationRelativeTo(null);
		ImageIcon image = new ImageIcon("src/pictures/warn.png");
		jDialog.setIconImage(image.getImage());

		JLabel jLabel = new JLabel("δ�������룡");
		jLabel.setFont(new Font("�п�", 0, 21));
		jLabel.setBounds(73, 0, 200, 100);
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

	// �����ȷ�����벻һ��ʱ������ʾ��
	public void differentPasswordJDialog(JFrame jFrame) {

		JDialog jDialog = new JDialog(jFrame, "��ʾ");
		jDialog.setLayout(null);
		jDialog.setSize(300, 200);
		jDialog.setLocationRelativeTo(null);
		ImageIcon image = new ImageIcon("src/pictures/warn.png");
		jDialog.setIconImage(image.getImage());

		JLabel jLabel = new JLabel("�������벻һ�£�");
		jLabel.setFont(new Font("�п�", 0, 21));
		jLabel.setBounds(63, 0, 200, 100);
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

	// �Ѵ����˺ŵ�����ʾ�Ի���
	public void existAccountJDialog(JFrame jFrame) {

		JDialog jDialog = new JDialog(jFrame, "��ʾ");
		jDialog.setLayout(null);
		jDialog.setSize(300, 200);
		jDialog.setLocationRelativeTo(null);
		ImageIcon image = new ImageIcon("src/pictures/warn.png");
		jDialog.setIconImage(image.getImage());

		JLabel jLabel = new JLabel("���˺��Ѵ��ڣ�");
		jLabel.setFont(new Font("�п�", 0, 21));
		jLabel.setBounds(73, 0, 200, 100);
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

	// �ɹ�ע��Ի���
	public void successRegisterJDialog(JFrame jFrame) {

		JDialog jDialog = new JDialog(jFrame, "��ʾ");
		jDialog.setLayout(null);
		jDialog.setSize(300, 200);
		jDialog.setLocationRelativeTo(null);
		ImageIcon image = new ImageIcon("src/pictures/warn.png");
		jDialog.setIconImage(image.getImage());

		JLabel jLabel = new JLabel("ע��ɹ���");
		jLabel.setFont(new Font("�п�", 0, 21));
		jLabel.setBounds(73, 0, 200, 100);
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
				// ������ʾ�Ի���
				jDialog.dispose();

			}
		});

		jDialog.setVisible(true);

	}

}
