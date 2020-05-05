package function;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

import frame.MainFrame;

/**
 * ʵ�ַ�����Ϣ�����浽��Ϣ��¼
 * 
 * @author 360��˳��
 * 
 * @date 2020/05/01
 *
 */
public class TextSend {

	JFrame mainFrame;
	JTextPane textShowPane;
	JTextPane textInputPane;
	String userName;

	public TextSend(JTextPane textShowPane, JTextPane textInputPane, String userName) {
		this.textShowPane = textShowPane;
		this.textInputPane = textInputPane;
		this.userName = userName;
	}

	public void sendText() {

		//��������Ϊ��
		if (!("".equals(textInputPane.getText()))) {

			// ��ȡ���ڲ��������ڸ�ʽ
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleAttributeSet attributeSet = new SimpleAttributeSet();

			// ��ȡ����
			String inputText = dateFormat.format(date) + "\n";

			//������ʽ
			Pattern pattern = Pattern.compile(".+[\\.].+");
			Matcher matcher = pattern.matcher(textInputPane.getText());

			// �ж��Ƿ�Ϊ�ļ�
			boolean isFile = false;
			// �ж��Ƿ�Ϊ��һ���ļ�
			boolean isFirst = true;
			while (matcher.find()) {
				isFile = true;

				// ����ļ���
				int index = matcher.group().lastIndexOf("\\");
				String fileName = matcher.group().substring(index + 1);

				// ͼƬ�����
				if (matcher.group().endsWith(".png") || matcher.group().endsWith(".jpg")
						|| matcher.group().endsWith(".jpeg") || matcher.group().endsWith("gif")) {

					Document document = textShowPane.getDocument();

					try {

						if (isFirst) {
							isFirst = false;
							document.insertString(document.getLength(), inputText, new SimpleAttributeSet());
							new RecordsEcho(userName, textShowPane).writeImage(matcher.group(), fileName);
							document.insertString(document.getLength(), "\n", new SimpleAttributeSet());

						}

						else {
							new RecordsEcho(userName, textShowPane).writeImage(matcher.group(), fileName);
							document.insertString(document.getLength(), "\n", new SimpleAttributeSet());

						}

					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {// �ļ������

					Document document = textShowPane.getDocument();

					try {

						if (isFirst) {
							isFirst = false;
							document.insertString(document.getLength(), inputText, new SimpleAttributeSet());
							new RecordsEcho(userName, textShowPane).writeFile(matcher.group(), fileName);
							document.insertString(document.getLength(), "\n", new SimpleAttributeSet());

						}

						else {
							new RecordsEcho(userName, textShowPane).writeFile(matcher.group(), fileName);
							document.insertString(document.getLength(), "\n", new SimpleAttributeSet());

						}

					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}

			if (!isFile) {

				// ʵ�ַ����ı�̫���Զ�����
				String str = "";

				for (int i = 0; i < textInputPane.getText().length(); i++) {

					if (i != 0 && i % 30 == 0)
						str += "\n";

					str += textInputPane.getText().charAt(i);

				}

				Document document = textShowPane.getDocument();

				try {
					document.insertString(document.getLength(), inputText + str + "\n\n", attributeSet);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// ����Ϣ���浽��Ӧ���û�������ʷ��¼txt�ļ�
			SaveRecords records = new SaveRecords(userName, inputText + textInputPane.getText() + "\n\n");
			records.saveRecords();

			textInputPane.setText("");

		} else {
			new MainFrame();
			MainFrame.warnJDialog("���ܷ��Ϳհ���Ϣ��");
		}
	}

}
