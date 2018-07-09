package hx.com.example.rule.common.event;


import hx.com.example.rule.common.message.RuleNotifyMessage;
import hx.thread.executor.event.ThreadPoolEvent;

/**
 * @Author mingliang
 * @Date 2018-01-29 11:08
 */
public class RuleAsynNoticeEvent extends ThreadPoolEvent<RuleNotifyMessage> {

    public RuleAsynNoticeEvent(RuleNotifyMessage ruleNotifyMessage) {
        super(ruleNotifyMessage);
    }

    @Override
    public void executeEvent() {

    }
}
