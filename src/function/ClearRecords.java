package function;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextPane;

/**
 * ����ʵ�������ʷ��¼�Ĺ���
 * 
 * @author 360��˳��
 * 
 * @date 2020/05/03
 *
 */
public class ClearRecords {

	private JTextPane textShowPane;

	private JTextPane recordsShowPane;
	
	private String userName;
	
	public ClearRecords(String userName,JTextPane textShowPane,JTextPane recordsShowPane) {
		this.userName = userName;
		this.textShowPane = textShowPane;
		this.recordsShowPane = recordsShowPane;
	}

	//��չʾ��塢������塢������Ϣȫ�����
	public void clear() {

		textShowPane.setText("");

		recordsShowPane.setText("");

		String path = "src/txt/" + userName + ".txt";

		File file = new File(path);

		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write("");;
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
