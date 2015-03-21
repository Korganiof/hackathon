package fi.utu.ville.exercises.hope;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;

import fi.utu.ville.exercises.helpers.ExerciseExecutionHelper;
import fi.utu.ville.exercises.hope.jsmap.JsMapStack;
import fi.utu.ville.exercises.model.ExecutionSettings;
import fi.utu.ville.exercises.model.ExecutionState;
import fi.utu.ville.exercises.model.ExecutionStateChangeListener;
import fi.utu.ville.exercises.model.Executor;
import fi.utu.ville.exercises.model.ExerciseException;
import fi.utu.ville.exercises.model.SubmissionListener;
import fi.utu.ville.exercises.model.SubmissionType;
import fi.utu.ville.standardutils.Localizer;
import fi.utu.ville.standardutils.TempFilesManager;

public class HopeExecutor extends VerticalLayout implements
		Executor<HopeExerciseData, HopeSubmissionInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2682119786422750060L;
		
	private final ExerciseExecutionHelper<HopeSubmissionInfo> execHelper =

	new ExerciseExecutionHelper<HopeSubmissionInfo>();

	private final TextField answerField = new TextField();
	private Button btn;
	private Button saksa;
	//private Button neuvostoliitto;
	private Button suomi;
	private Button testi;

	

	
	public HopeExecutor() {

	}

	@Override
	public void initialize(Localizer localizer,
			HopeExerciseData exerciseData, HopeSubmissionInfo oldSubm,
			TempFilesManager materials, ExecutionSettings fbSettings)
			throws ExerciseException {
		answerField.setCaption(localizer.getUIText(HopeUiConstants.ANSWER));
		doLayout(exerciseData, oldSubm != null ? oldSubm.getAnswer() : "");
	}

	private void doLayout(HopeExerciseData exerciseData, String oldAnswer) {
		JsMapStack map = new JsMapStack();
		this.addComponent(map);
		btn = new Button("Lisää");		
		btn.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				Window subWindow = new Window("Sub-window");
				subWindow.setModal(true);

		        VerticalLayout subContent = new VerticalLayout();
		        subContent.setMargin(true);
		        subWindow.setContent(subContent);
		        
		        saksa = new Button("Saksa");
		        saksa.addClickListener(new ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						//todo mitä tapahtu kun painaa nappia "Saksa" 
						
					}
				});
		        // Tässä lisätään modalin / popup windowin sisältö
		        //neuvostoliitto = new Button("Neuvostoliitto");
		        suomi = new Button("Suomi");
		        testi = new Button("Testi");
		        Label answer = new Label("Mikä näistä maista hävisi toisen maailmansodan?");
		        subContent.addComponent(answer);	
		        subContent.addComponent(saksa);
		        //subContent.addComponent(neuvostoliitto);
		        subContent.addComponent(suomi);
		        subContent.addComponent(testi);

		        getUI().addWindow(subWindow);
	
				
			}
		});

		this.addComponent(new Label(exerciseData.getQuestion()));
		answerField.setValue(oldAnswer);
		this.addComponent(btn);

	
	}

	@Override
	public void registerSubmitListener(
			SubmissionListener<HopeSubmissionInfo> submitListener) {
		execHelper.registerSubmitListener(submitListener);
	}

	@Override
	public Layout getView() {
		return this;
	}

	@Override
	public void shutdown() {
		// nothing to do here
	}

	@Override
	public void askReset() {
		// nothing to do here
	}

	@Override
	public ExecutionState getCurrentExecutionState() {
		return execHelper.getState();
	}

	@Override
	public void askSubmit(SubmissionType submType) {
		double corr = 1.0;

		String answer = answerField.getValue();
		execHelper.informOnlySubmit(corr, new HopeSubmissionInfo(answer),
				submType, null);

	}

	@Override
	public void registerExecutionStateChangeListener(
			ExecutionStateChangeListener execStateListener) {
		execHelper.registerExerciseExecutionStateListener(execStateListener);

	}

}
