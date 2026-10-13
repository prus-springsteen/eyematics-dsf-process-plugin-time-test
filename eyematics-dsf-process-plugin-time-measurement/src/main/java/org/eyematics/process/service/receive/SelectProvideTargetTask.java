package org.eyematics.process.service.receive;

import dev.dsf.bpe.v1.ProcessPluginApi;
import dev.dsf.bpe.v1.activity.AbstractServiceDelegate;
import dev.dsf.bpe.v1.variables.Target;
import dev.dsf.bpe.v1.variables.Variables;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.eyematics.process.utils.bpe.SelectTarget;
import org.eyematics.process.utils.measurement.TimeMeasurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SelectProvideTargetTask extends AbstractServiceDelegate {

    private static final Logger logger = LoggerFactory.getLogger(SelectProvideTargetTask.class);
    private final TimeMeasurement receiveMeasurementConfig;

    public SelectProvideTargetTask(ProcessPluginApi api, TimeMeasurement receiveMeasurementConfig) {
        super(api);
        this.receiveMeasurementConfig = receiveMeasurementConfig;
    }

    @Override
    protected void doExecute(DelegateExecution delegateExecution, Variables variables) throws BpmnError, Exception {
        this.receiveMeasurementConfig.setDataPoint(SelectProvideTargetTask.class.getSimpleName() + "_START");
        logger.info("-> Selecting the target for the acknowledge process.");
        Target target = SelectTarget.getRequestTarget(this.api, variables, false);
        variables.setTarget(target);
        this.receiveMeasurementConfig.setDataPoint(SelectProvideTargetTask.class.getSimpleName() + "_END");
        this.receiveMeasurementConfig.writeData();
        this.receiveMeasurementConfig.reset();
    }
}
