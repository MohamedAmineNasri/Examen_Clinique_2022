package tn.esprit.examenclinique2022.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LoggingAspect {
    @After("execution( * tn.esprit.examenclinique2022.service.ServiceImp.add*(..))")
    public void logMethodeExit(JoinPoint joinPoint) {
        log.info("Methode execute");
    }
    @Before("execution( * tn.esprit.examenclinique2022.service.ServiceImp.add*(..))")
    public void logMethodeEntry(JoinPoint joinPoint) {
        log.info("Methode execute");
    }

    @Around("execution( * tn.esprit.examenclinique2022.service.ServiceImp.add*(..))")
    //bich nakho wa9it ili bdat fih methode w wa9it ili wfat fih methode w natra7hom min b3adhom
    public Object profile(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info("Arround method execution " +(start-end));
        return obj;
    }
}
