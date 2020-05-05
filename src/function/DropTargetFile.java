package function;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.List;

import javax.swing.JTextPane;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

/**
 * ����ʵ����ק�ļ�������ճ������
 * 
 * ���ڸù��ܵ�һ��ʵ�֣����Բ��ִ����ǰ�ש������
 * 
 * @author 360��˳��
 *
 * @date 2020/05/03
 */
public class DropTargetFile implements DropTargetListener {

	/** ������ʾ��ק���ݵ���� */
    private JTextPane inputPane;

    public DropTargetFile(JTextPane inputPane) {
        this.inputPane = inputPane;
    }

    public void dragEnter(DropTargetDragEvent dtde) {
    	
    }

    public void dragOver(DropTargetDragEvent dtde) {

    }

    public void dragExit(DropTargetEvent dte) {

    }

    public void dropActionChanged(DropTargetDragEvent dtde) {

    }

    public void drop(DropTargetDropEvent dtde) {
    	
        boolean isAccept = false;

        try {
            /*
                                * �ж���קĿ���Ƿ�֧���ļ��б����ݣ�����ק���Ƿ����ļ����ļ���, ֧��ͬʱ��ק�����
             */
            if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                // ������קĿ������
                dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                isAccept = true;

                // ���ļ����ϵ���ʽ��ȡ����
                @SuppressWarnings("unchecked")
				List<File> files = (List<File>) dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);

                // ���ļ�·��������ı�����
                if (files != null && files.size() > 0) {
                    StringBuilder filePaths = new StringBuilder();
                    for (File file : files) {
                        filePaths.append(file.getAbsolutePath() + "\n");
                    }
                    
                    Document document = inputPane.getDocument();
                    
                    document.insertString(document.getLength(), filePaths.toString(), new SimpleAttributeSet());
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        // ����˴���ק�������Ǳ����ܵ�, �����������ק��ɣ�������ܻῴ����קĿ�귵��ԭλ��, ����Ӿ�����Ϊ�ǲ�֧����ק�Ĵ���Ч����
        if (isAccept) {
            dtde.dropComplete(true);
        }
    }

	
}
