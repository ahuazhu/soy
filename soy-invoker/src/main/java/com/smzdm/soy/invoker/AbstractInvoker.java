package com.smzdm.soy.invoker;

import com.smzdm.soy.domain.InvokerContext;
import com.smzdm.soy.exception.SoyException;
import com.smzdm.soy.exception.SoyTimeoutException;

import java.util.concurrent.*;

/**
 * Created by zhengwenzhu on 2016/10/31.
 */
public abstract class AbstractInvoker implements Invoker {

    private ExecutorService es = Executors.newFixedThreadPool(30);

    public String invoke(final InvokerContext context) throws SoyException {

        Callable<String> task = new Callable<String>() {
            public String call() throws Exception {
                return doInvoke(context);
            }
        };
        Future<String> result = es.submit(task);

        try {
            return result.get(context.getInvokerConfig().getTimeout(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new SoyTimeoutException(e);
        } catch (ExecutionException e) {
            throw new SoyException(e);
        } catch (TimeoutException e) {
            throw new SoyTimeoutException(e);
        } finally {
            abort(context);
            result.cancel(false);
        }
    }

    protected abstract String doInvoke(InvokerContext context);

    protected abstract void abort(InvokerContext context);

}
