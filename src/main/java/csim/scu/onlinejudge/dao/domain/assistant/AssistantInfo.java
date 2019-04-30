package csim.scu.onlinejudge.dao.domain.assistant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@AllArgsConstructor
@Getter
@Setter
public class AssistantInfo {

    private String assistantId;
    private String assistantName;
}
