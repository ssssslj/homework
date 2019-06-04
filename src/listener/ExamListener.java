package listener;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.opensymphony.xwork2.ActionContext;

import dao.ExamDAO;
import dao.impl.ExamDAOImpl;
import service.ExamManager;
import service.impl.ExamManagerImpl;
import util.Config1;
import util.ConfigUtil;
import vo.Exam;

public class ExamListener implements ServletContextListener {
	public static HashMap<Integer, Timer> timers = new HashMap<>();
	private Timer timer;
	private ExamManager eManager;

	
	
	public ExamManager geteManager() {
		return eManager;
	}

	public void seteManager(ExamManager eManager) {
		this.eManager = eManager;
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		Config1 config=new Config1();
		 String configPath = sce.getServletContext().getRealPath("") + Config1.CONFIG_FILE;
		File file=new File(configPath);
		
		if (!file.exists()) {
			ConfigUtil.writeConfig(file, config);
		}
		config=ConfigUtil.parseConfig(file);
		
		sce.getServletContext().setAttribute("config", config);
		
		
		// 注入eManager
		XmlWebApplicationContext xmlctx = new XmlWebApplicationContext();   
		xmlctx.setConfigLocations(new String[] {"/WEB-INF/applicationContext.xml"});   
		xmlctx.setServletContext(sce.getServletContext());   
		xmlctx.refresh();  
		eManager = (ExamManager)xmlctx.getBean("eManager");
		
		this.timer = new Timer();// 设置计时器，固定间隔更新自动开始考试列表
		
		this.timer.schedule(new TimerTask() {

			@Override
			public void run() {
				
				for (Timer timer : timers.values()) {
					timer.cancel();
				}
				List<Exam> exams = eManager.getAllExams();

				for (Exam exam : exams) {
					if (exam.getAutoStart() && (!exam.getStarted()) && (!exam.getFinished())) {
						if (exam.getStartTime().compareTo(new Date()) < 0) {
							continue;
						}
						Timer timer = new Timer();
						CheckExamTask task = new CheckExamTask();
						task.setmExam(exam);
						timer.schedule(task, exam.getStartTime());
						timers.put(exam.getId(), timer);
					}
				}

			}
		}, 20000, 60 * 1000);

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		this.timer.cancel();
		for (Timer timer : timers.values()) {
			timer.cancel();
		}

	}

	private Exam myExam;

	public Exam getExam() {
		return myExam;
	}

	public void setExam(Exam exam) {
		this.myExam = exam;
	}

	class CheckExamTask extends TimerTask {
		private Exam mExam;

		public Exam getmExam() {
			return mExam;
		}

		public void setmExam(Exam mExam) {
			this.mExam = mExam;
		}

		@Override
		public void run() {
			System.out.println("start:");
			if (this.mExam == null) {
				return;
			}

			//ExamManager examManager = new ExamManagerImpl();
			List<Exam> exams = eManager.getAllExams();

			for (Exam exam : exams) {
				// 有考试正在进行，此考试无法自动开始
				if (exam.getId() != this.mExam.getId() && exam.getStarted() && (!exam.getFinished())) {
					System.out.println("考试时间存在冲突");
					return;
				}
			}
			System.out.println("startExam: " + mExam.getId() + " " + mExam.getName());
			// 无正在进行的考试
			this.mExam.setStarted(true);
			eManager.updateExam(mExam);

		}
	}

}
