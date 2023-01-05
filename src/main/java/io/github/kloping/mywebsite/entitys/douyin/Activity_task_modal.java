package io.github.kloping.mywebsite.entitys.douyin;

public class Activity_task_modal {
	private String backgroundImg;
	private String localStorageName;
	private String name;
	private Boolean open;
	private TaskId taskId;
	private ActionName actionName;
	private String group;

	public String getBackgroundImg(){
		return this.backgroundImg;
	}

	public Activity_task_modal setBackgroundImg(String backgroundImg) {
		this.backgroundImg = backgroundImg;
		return this;
	}

	public String getLocalStorageName(){
		return this.localStorageName;
	}

	public Activity_task_modal setLocalStorageName(String localStorageName) {
		this.localStorageName = localStorageName;
		return this;
	}

	public String getName(){
		return this.name;
	}

	public Activity_task_modal setName(String name) {
		this.name = name;
		return this;
	}

	public Boolean getOpen(){
		return this.open;
	}

	public Activity_task_modal setOpen(Boolean open) {
		this.open = open;
		return this;
	}

	public TaskId getTaskId(){
		return this.taskId;
	}

	public Activity_task_modal setTaskId(TaskId taskId) {
		this.taskId = taskId;
		return this;
	}

	public ActionName getActionName(){
		return this.actionName;
	}

	public Activity_task_modal setActionName(ActionName actionName) {
		this.actionName = actionName;
		return this;
	}

	public String getGroup(){
		return this.group;
	}

	public Activity_task_modal setGroup(String group) {
		this.group = group;
		return this;
	}
}