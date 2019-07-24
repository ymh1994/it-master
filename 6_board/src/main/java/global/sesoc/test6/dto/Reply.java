package global.sesoc.test6.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {

	private int replyseq;
	private String userid;
	private int boardseq;
	private String content;
	private String regdate;
	
	
}
