package top.niunaijun.bcore.fake.service.context.providers;

import android.os.IInterface;

import java.lang.reflect.Method;

import black.android.content.AttributionSource;
import top.niunaijun.bcore.BlackBoxCore;
import top.niunaijun.bcore.fake.hook.ClassInvocationStub;
import top.niunaijun.bcore.utils.compat.ContextCompat;

public class SystemProviderStub extends ClassInvocationStub implements BContentProvider {
    private IInterface mBase;

    @Override
    public IInterface wrapper(IInterface contentProviderProxy, String appPkg) {
        mBase = contentProviderProxy;

        injectHook();
        return (IInterface) getProxyInvocation();
    }

    @Override
    protected Object getWho() {
        return mBase;
    }

    @Override
    protected void inject(Object baseInvocation, Object proxyInvocation) { }

    @Override
    protected void onBindMethod() { }

    @Override
    public boolean isBadEnv() {
        return false;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("asBinder".equals(method.getName())) {
            return method.invoke(mBase, args);
        }

        if (args != null && args.length > 0) {
            Object arg = args[0];
            if (arg instanceof String) {
                args[0] = BlackBoxCore.getHostPkg();
            } else if (arg.getClass().getName().equals(AttributionSource.REF.getClazz().getName())) {
                ContextCompat.fixAttributionSourceState(arg, BlackBoxCore.getHostUid());
            }
        }
        return method.invoke(mBase, args);
    }
}
