package course.business;

public class Section {
	
	int sectionId;
	int sectionNum;
	int sectionLimit;
	int year;
	String courseName;
	String day;
	String semseter;
	String SemesterId;
	int roomId;
	String buildingName;
	int buildingId;
	String dept_id;
	String lectureType;
	String starttime;
	String endTime;
	String instructor;
	
	
	public int getBuildingId() {
		return buildingId;
	}


	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}


	public Section(String buildingName, int buildingId) {
		super();
		this.buildingName = buildingName;
		this.buildingId = buildingId;
	}


	public Section(String semseter, String semesterId) {
		super();
		this.semseter = semseter;
		SemesterId = semesterId;
	}


	public String getSemesterId() {
		return SemesterId;
	}


	public void setSemesterId(String semesterId) {
		SemesterId = semesterId;
	}


	public Section(int sectionId, int sectionNum, int sectionLimit, int year, String courseName,
			String day, String semseter, int roomId, String buildingName, String dept_id, String lectureType,
			String starttime, String endTime, String instructor) {
		super();
		this.sectionId = sectionId;
		this.sectionNum = sectionNum;
		this.sectionLimit = sectionLimit;
		this.year = year;
		this.courseName = courseName;
		this.day = day;
		this.semseter = semseter;
		this.roomId = roomId;
		this.buildingName = buildingName;
		this.dept_id = dept_id;
		this.lectureType = lectureType;
		this.starttime = starttime;
		this.endTime = endTime;
		this.instructor = instructor;
	}
	
	
	public Section(int sectionId, int year, String day, String semseter, String starttime, String endTime) {
		super();
		this.sectionId = sectionId;
		this.year = year;
		this.day = day;
		this.semseter = semseter;
		this.starttime = starttime;
		this.endTime = endTime;
	}


	public Section(int sectionId, int sectionNum, int sectionLimit, int year, String semseter, int roomId,String courseName,String dept_id) {
		super();
		this.sectionId = sectionId;
		this.sectionNum = sectionNum;
		this.sectionLimit = sectionLimit;
		this.year = year;
		this.semseter = semseter;
		this.roomId = roomId;
		this.courseName=courseName;
		this.dept_id=dept_id;
	}

	public Section() {
		super();
	}
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public int getSectionNum() {
		return sectionNum;
	}
	public void setSectionNum(int sectionNum) {
		this.sectionNum = sectionNum;
	}
	public int getSectionLimit() {
		return sectionLimit;
	}
	public void setSectionLimit(int sectionLimit) {
		this.sectionLimit = sectionLimit;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getSemseter() {
		return semseter;
	}
	public void setSemseter(String semseter) {
		this.semseter = semseter;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	public String getLectureType() {
		return lectureType;
	}
	public void setLectureType(String lectureType) {
		this.lectureType = lectureType;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
}
