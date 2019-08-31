package za.co.jaredfishy.mslights.application.domain.command;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class StartFlowCommand extends Command {

    private final int count;
    private final FlowAction action;
    private final List<FlowExpression> flowExpressionList;

    public StartFlowCommand(int count, FlowAction action, List<FlowExpression> flowExpressionList) {
        super(CommandMethod.START_FLOW);
        this.count = count;
        this.action = action;
        this.flowExpressionList = flowExpressionList;
    }


    @Override
    public JSONArray getParams() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(count);
        jsonArray.put(action.getAction());
        List<String> stringList = new ArrayList<>();
        for (FlowExpression flowExpression : flowExpressionList) {
            stringList.add(flowExpression.getFlowString());
        }
        jsonArray.put(String.join(",", stringList));
        return jsonArray;
    }
}
