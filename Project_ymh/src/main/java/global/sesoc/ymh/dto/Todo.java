package global.sesoc.ymh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

	private int todoSeq; //일련번호
	private String userId; //사용자ID
	private String todoData;//해야할일
	private String regDate;//작성일
	private String importance;//중요도
	private String deadLine;//마감일
	private String comments;//코멘트
	private String result;//결과
	@Override
	public String toString() {
		return "일련번호" + todoSeq + " ID=" + userId + " 할 일=" + todoData + " 시작일 = " + regDate
				+ " 중요도 = " + importance + " 마감일 = " + deadLine + " 코멘트 = " + comments + " 결과 = "
				+ result;
	}
	
}
