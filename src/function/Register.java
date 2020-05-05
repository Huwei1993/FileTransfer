package function;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * �ļ���������ע�Ṧ��
 * 
 * @author��360��˳��
 * 
 * @date��2020/04/28 ~ 2020/04/29
 * 
 */

public class Register {

	
	JTextField userNameTextField;
	JPasswordField passwordField;
	JPasswordField passwordAgainField;

	//��RegisterFrame�����������
	public Register(JTextField userNameTextField, JPasswordField passwordField, JPasswordField passwordAgainField) {
		this.userNameTextField = userNameTextField;
		this.passwordField = passwordField;
		this.passwordAgainField = passwordAgainField;
	}

	
	//�ж��˺��Ƿ�Ϊ�շ���
	public boolean isEmptyUserName() {
		if (userNameTextField.getText().equals(""))
			return true;
		else
			return false;
	}

	
	//�ж������Ƿ�Ϊ�շ���
	public boolean isEmptyPassword() {
		//����������ı�Ҫ�Ƚ���ת��Ϊ�ַ���
		if ("".equals(new String(passwordField.getPassword())) || "".equals(new String(passwordAgainField.getPassword())))
			return true;
		else
			return false;
	}

	
	//�ж���������������Ƿ�һ�·���
	public boolean isSamePassWord() {
		
		//����������ı�Ҫ�Ƚ���ת��Ϊ�ַ���
		if (new String(passwordField.getPassword()).equals(new String(passwordAgainField.getPassword())))
			return true;
		else
			return false;
	}

	
	//�ж��˺��Ƿ��Ѵ��ڷ���
	public boolean isExistAccount() {
		File file = new File("src/txt/userInformation.txt");

		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		boolean vis = false;

		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);

			//������ʽ
			Pattern pattern = Pattern.compile("�û�����.+");

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				Matcher matcher = pattern.matcher(str);
				if (matcher.find()) {
					if (("�û�����" + userNameTextField.getText()).equals(matcher.group())) {
						vis = true;
						break;
					}
				}

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		if (!vis) {
			return false;
		} else {
			return true;
		}

	}
	

	//������Ϣ������
	public void saveInformation() {
		File file = new File("src/txt/userInformation.txt");

		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			fileWriter = new FileWriter(file, true);
			bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write("�û�����" + userNameTextField.getText());
			bufferedWriter.newLine();
			bufferedWriter.write("���룺" + new String(passwordField.getPassword()));
			bufferedWriter.newLine();
			bufferedWriter.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
