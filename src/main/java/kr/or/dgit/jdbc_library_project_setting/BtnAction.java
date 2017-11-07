package kr.or.dgit.jdbc_library_project_setting;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import kr.or.dgit.jdbc_library_project_setting.service.DbService;
import kr.or.dgit.jdbc_library_project_setting.service.ExportService;
import kr.or.dgit.jdbc_library_project_setting.service.ImportService;
import kr.or.dgit.jdbc_library_project_setting.service.InitService;
import kr.or.dgit.jdbc_library_project_setting.service.InitServiceTrigger;

public class BtnAction extends AbstractAction {

	public BtnAction(String name) {
		super(name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DbService service=null;
		DbService service2=null;
		//초기화, 백업, 복원
		switch(e.getActionCommand()){
		case "초기화":
			service=InitService.getInstance();

			service2=InitServiceTrigger.getInstance();

			break;
		case "백업":
			service=ExportService.getInstance();
			break;
		case "복원":
			service=ImportService.getInstance();
			break;
		}
		service.service();
		service2.service();
		JOptionPane.showMessageDialog(null, e.getActionCommand()+"가(이) 완료 되었습니다.");
	}
}
