package q005;

/**
 * 作業時間管理クラス
 * 自由に修正してかまいません
 */
public class WorkData {
    /** 社員番号 */
    private String number;

    /** 部署 */
    private String department;

    /** 役職 */
    private String position;

    /** Pコード */
    private String pCode;

    /** 作業時間(分) */
    private int workTime;

    public WorkData(String number, String department, String position, String pCode, int workTime) {
        this.number = number;
        this.department = department;
        this.position = position;
        this.pCode = pCode;
        this.workTime = workTime;
    }

    public String getNumber() {
        return this.number;
    }

    public String getDepartment() {
        return this.department;
    }

    public String getPosition() {
        return this.position;
    }

    public String getPCode() {
        return this.pCode;
    }

    public int getWorkTime() {
        return this.workTime;
    }

    public static WorkData of(String data) {
        String[] workData = data.split(",");
        return new WorkData(
                workData[0],
                workData[1],
                workData[2],
                workData[3],
                Integer.parseInt(workData[4])
        );
    }
}
