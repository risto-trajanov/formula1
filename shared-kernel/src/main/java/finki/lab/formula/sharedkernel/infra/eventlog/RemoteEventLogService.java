package finki.lab.formula.sharedkernel.infra.eventlog;


import finki.lab.formula.sharedkernel.domain.base.RemoteEventLog;

public interface RemoteEventLogService {

    String source();

    RemoteEventLog currentLog(long lastProcessedId);

}
