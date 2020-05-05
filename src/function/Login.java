package function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * �ļ��������ֵ�¼����
 * 
 * @author��360��˳��
 * 
 * @date: 2020/04/29
 * 
 */

public class Login {

	JTextField userNameTextField;
	JPasswordField passwordField;

	public Login(JTextField userNameTextField, JPasswordField passwordField) {
		this.userNameTextField = userNameTextField;
		this.passwordField = passwordField;
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
		if ("".equals(new String(passwordField.getPassword())))
			return true;
		else
			return false;
	}
	
	
	// ��ѯ�Ƿ���ڸ��˺�����
	public boolean queryInformation() {

		File file = new File("src/txt/userInformation.txt");
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		boolean vis = false;
		try {

			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);

			Pattern userNamePattern = Pattern.compile("�û�����.+");
			Pattern passwordPattern = Pattern.compile("���룺.+");

			String str1 = null;
			while ((str1 = bufferedReader.readLine()) != null) {
				
				Matcher userNameMatcher = userNamePattern.matcher(str1);
				
				if(userNameMatcher.find()) {
					
					if (("�û�����" + userNameTextField.getText()).equals(userNameMatcher.group())) {
						
						String str2 = bufferedReader.readLine();
						Matcher passwordMatcher = passwordPattern.matcher(str2);
						
						if(passwordMatcher.find()) {
							if (("���룺" + new String(passwordField.getPassword())).equals(passwordMatcher.group())) {
								vis = true;
								break;
							}
						}
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

		if (vis)
			return true;
		else
			return false;
		
	}

}
