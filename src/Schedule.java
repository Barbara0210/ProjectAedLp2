import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
public class Schedule {
  private Integer scheduleId;
  private String startTime;
  private String endTime;

  public Schedule(Integer scheduleId, String startTime, String endTime) {
    this.scheduleId = scheduleId;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public Integer getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(Integer scheduleId) {
    this.scheduleId = scheduleId;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }
  public long getDuration() {
    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    try {
      Date start = format.parse(startTime);
      Date end = format.parse(endTime);
      long diffInMillies = Math.abs(end.getTime() - start.getTime());
      return TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
    } catch (ParseException e) {
      e.printStackTrace();
      return -1;
    }
  }
  @Override
  public String toString() {
    return "Schedule Id: " + this.scheduleId + ", Start Time: " + this.startTime + ", End Time: " + this.endTime;
  }
}
