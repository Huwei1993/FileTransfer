package function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

/**
 * ����ʵ�ֲ�����ʷ��¼
 * 
 * ��δ���˵ʵ����д���е��㣬��������Ҫ�Ż��������е��Ѹ㣬�������ɣ������ſ���
 * 
 * 
 * @author 360��˳��
 *
 * @date 2020/05/02
 */
public class FindRecords {

	private JTextPane recordsShowPane;
	private String userName;
	private String keywords;

	public FindRecords(JTextPane recordsShowPane, String userName, String keywords) {
		this.recordsShowPane = recordsShowPane;
		this.userName = userName;
		this.keywords = keywords;
	}

	// �÷������������ı���ͼƬ���ļ�����ΪҪ����ʹ�ã�����д��һ����
	public void find() {

		// ������ҵĲ�Ϊ��
		if (!(keywords.equals(""))) {

			// ��������˺���ʷ��¼
			File file = new File("src/txt/" + userName + ".txt");

			FileReader fileReader = null;
			BufferedReader bufferedReader = null;

			try {

				fileReader = new FileReader(file);
				bufferedReader = new BufferedReader(fileReader);

				String str = "";
				String str1 = null;

				// �Ȱ������ı��ҵ�
				while ((str1 = bufferedReader.readLine()) != null) {

					if (str1.equals(""))
						str += "\n";
					else
						str = str + str1 + "\n";

				}

				// ������ʽƥ��Ҫ�ҵ�����
				Pattern pattern = Pattern.compile(".+\n.*" + keywords + ".*\n\n");
				Matcher matcher = pattern.matcher(str);

				// �����û���ҵ�
				boolean isExist = false;

				// ����Ƿ��һ���ҵ�
				boolean oneFind = false;

				// ����ҵ���
				while (matcher.find()) {

					isExist = true;
					// ������ʽƥ���Ƿ�Ϊ�ļ�ͼƬ·��
					Pattern pattern1 = Pattern.compile(".+[\\.].+");
					Matcher matcher1 = pattern1.matcher(matcher.group());

					// ������ļ�����ͼƬ
					if (matcher1.find()) {

						// ��ȡ����
						int index3 = matcher.group().indexOf("\n");
						String date = matcher.group().substring(0, index3);

						// ����ļ���
						int index1 = matcher.group().lastIndexOf("\\");
						int index2 = matcher.group().lastIndexOf("\n\n");
						String fileName = matcher.group().substring(index1 + 1, index2);

						// ͼƬ�����
						if (fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")
								|| fileName.endsWith("gif")) {

							Pattern pattern2 = Pattern.compile("[emoji_].+[\\.].+");
							Matcher matcher2 = pattern2.matcher(fileName);

							// ����Ǳ��������Ҫ�������
							if (matcher2.find()) {

								if (!oneFind) {

									// д������
									recordsShowPane.setText(date + "\n");

									// ��������������
									RecordsEcho echo = new RecordsEcho(fileName, recordsShowPane);
									echo.writeEmoji(matcher1.group());

									Document document = recordsShowPane.getDocument();
									
									try {
										document.insertString(document.getLength(), "\n", new SimpleAttributeSet());
									} catch (BadLocationException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									
									oneFind = true;

								} else { // ���ǵ�һ�ξ�ֱ��д��

									Document document = recordsShowPane.getDocument();

									try {

										document.insertString(document.getLength(), date + "\n",
												new SimpleAttributeSet());

										RecordsEcho echo = new RecordsEcho(fileName, recordsShowPane);
										echo.writeEmoji(matcher1.group());
										
										document.insertString(document.getLength(), "\n", new SimpleAttributeSet());



									} catch (BadLocationException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								}

							}

							// ���Ǳ������ͼƬ
							else {

								// ����ǵ�һ���ҵ���Ҫ�������д��
								if (!oneFind) {
									// д������
									recordsShowPane.setText(date + "\n");

									// ����ͼƬ������
									RecordsEcho echo = new RecordsEcho(fileName, recordsShowPane);
									echo.writeImage(matcher1.group(), fileName);

									// ����
									Document document = recordsShowPane.getDocument();

									try {
										document.insertString(document.getLength(), "\n", new SimpleAttributeSet());
									} catch (BadLocationException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									// ��ǲ��ǵ�һ����
									oneFind = true;

								} else { // ���ǵ�һ���ҵ���ֱ��д��

									Document document = recordsShowPane.getDocument();

									try {
										document.insertString(document.getLength(), date + "\n",
												new SimpleAttributeSet());

										RecordsEcho echo = new RecordsEcho(fileName, recordsShowPane);
										echo.writeImage(matcher1.group(), fileName);

										document.insertString(document.getLength(), "\n", new SimpleAttributeSet());

									} catch (BadLocationException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}

							}

						} else { // �ļ������

							// ��һ���ҵ�
							if (!oneFind) {

								// ��ղ�д������
								recordsShowPane.setText(date + "\n");

								// �����ļ�ͼƬ�Լ�����
								RecordsEcho echo = new RecordsEcho(fileName, recordsShowPane);
								echo.writeFile(matcher1.group(), fileName);

								// ����
								Document document = recordsShowPane.getDocument();

								try {
									document.insertString(document.getLength(), "\n", new SimpleAttributeSet());
								} catch (BadLocationException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								oneFind = true;

							} else { // ���ǵ�һ���ҵ�
								Document document = recordsShowPane.getDocument();

								try {
									document.insertString(document.getLength(), date + "\n", new SimpleAttributeSet());

									RecordsEcho echo = new RecordsEcho(fileName, recordsShowPane);
									echo.writeFile(matcher1.group(), fileName);

									document.insertString(document.getLength(), "\n", new SimpleAttributeSet());

								} catch (BadLocationException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}

					} else { // ���ҵ����ı�
						
						String s = "";
						
						for(int i = 0; i < matcher.group().length(); i++) {
							
							//��Ϊ���ҵ����ַ����������ڣ�����Ҫ��20��ʼ
							if(i>20 && (i-20) % 30 == 0)
								s += "\n";
							
							s += matcher.group().charAt(i);
							
							
							
						}
						
						if (!oneFind) {
								
							recordsShowPane.setText(s);
							oneFind = true;
							
							
						} else {
							Document document = recordsShowPane.getDocument();

							try {
								document.insertString(document.getLength(), s, new SimpleAttributeSet());
							} catch (BadLocationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}

				}

				// �Ҳ��������
				if (!isExist)
					recordsShowPane.setText("\n\n\n                              ����ؼ�¼��");

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

		else { // ����Ϊ�յ����
			recordsShowPane.setText("\n\n\n                              ����ؼ�¼��");
		}

	}

}
