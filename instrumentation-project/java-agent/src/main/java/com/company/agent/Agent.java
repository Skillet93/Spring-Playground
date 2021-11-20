package com.company.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class Agent {
    public static void premain(String args, Instrumentation instrumentation) {
        System.out.println("Java agent is running.");

        AgentBuilder agentBuilder = new AgentBuilder.Default()
                .type(ElementMatchers.nameStartsWith("com.company"))
                .transform(((builder, typeDescription, classLoader, module) ->
                        builder.visit(
                                Advice
                                        .to(MyMethodMonitor.class)
                                        .on(ElementMatchers.hasMethodName("doSomething"))
                        )));

        agentBuilder.installOn(instrumentation);
    }

    public static void agentmain(String args, Instrumentation instrumentation) {
        premain(args, instrumentation);
    }
}
