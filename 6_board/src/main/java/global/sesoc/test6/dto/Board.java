package global.sesoc.test6.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {

	private int boardseq;
	private  String userid;
	private String title;
	private String content;
	private String regdate;
	private int viewcount;
	private int favorite;
	private String originalfile;
	private String savedfile;
	
	
	
}
