package hx.com.example.rule;

import org.drools.compiler.kie.builder.impl.InternalKieModule;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * drools6.4动态加载规则文件：第一次不命中，动态增加规则以后，命中
 * @Author mingliang
 * @Date 2017-12-15 17:06
 */
public class dynamicDrlTest {

    private static final String RULESFILE_NAME = "rules.drl";

    /**
     * 规则文件内容（可以从数据库中加载）
     */
//    private static final String rules      = "package com.caicongyang.drools.test; import hx.com.example.rule.MessageVoE; rule \"Hello World \" when message:MessageVoE (status == \"0\") then message.setStatus(\"1\"); System.out.println(\"-------------hello, Drools!\"); end";
//            +" rule \"goodeBy \" when message:MessageVoE (status == \"1\") then System.out.println(\"-------------GoodBye, Drools!\"); end";
    private static final String rules1      = "package com.caicongyang.drools.test; import hx.com.example.rule.MessageVoE; rule \"goodeBy \" when message:MessageVoE (status == \"1\") then System.out.println(\"-------------GoodBye, Drools!\"); end";

    private static final String rules = "package hx.com.rule.test; import hx.com.example.rule.MessageVoE; rule \"hello world\" when message:MessageVoE (status == \"0\") then message.setStatus(\"1\"); System.out.println(\"-------------hello, Drools!\"); end rule \"goodeBy\" when message:MessageVoE (status == \"1\") then System.out.println(\"-------------GoodBye, Drools!\"); end";


//    private static List<String> ruleList = new ArrayList<>();
//    private static List<ReleaseId> releaseIdList = new ArrayList<>();

    private static  KieServices kieServices = KieServices.Factory.get();
//    private static Map<Integer,KieSession> sessions = new HashMap<>();
    private static Map<Integer,KieContainer> kieContainerMap = new HashMap<>();


    public static void main(String[] args) throws Exception {
        initRule();
        Thread.sleep(5000);
        System.out.println("-----first fire start-------");
        while (true){
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in ));
            String read = null;
            System.out.print("输入数据：");
            try {
                read = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("输入数据："+read);
            if (Integer.valueOf(read) == 1){
                KieContainer kieContainer = kieContainerMap.get(1);
                KieSession session = kieContainer.newKieSession();
//                KieContainer kieContainer1 = kieContainerMap.get(2);
                try {
                    MessageVoE message = new MessageVoE();
                    message.setStatus("0");
                    session.insert(message);
                    session.fireAllRules();
                    KieSession session1 = kieContainer.newKieSession();
                    session1.insert(message);
                    session1.fireAllRules();
//                    session1.insert(message);
//                    session1.fireAllRules();
                    System.out.println(message.getStatus());
                } catch (Exception e) {
                } finally {
//                    session.dispose();
                    session.destroy();
                }
            }else if (Integer.valueOf(read) == 2){
                KieContainer kieContainer = kieContainerMap.get(2);
                KieSession session = kieContainer.newKieSession();
//                KieContainer kieContainer1 = kieContainerMap.get(1);
//                KieSession session1 = kieContainer1.newKieSession();
                try {
                    MessageVoE message = new MessageVoE();
                    message.setStatus("1");
                    session.insert(message);
                    session.fireAllRules();
//                    session1.insert(message);
//                    session1.fireAllRules();
                } catch (Exception e) {
                } finally {
//                    session.dispose();
                    session.destroy();
                }
            }else if (Integer.valueOf(read) == 3){
                System.out.println("退出！");
                System.exit(0);
            }

        }
    }


    public static void initRule() throws Exception{
        /**
         * 指定kjar包
         */
        final ReleaseId releaseId = kieServices.newReleaseId("com", "test", "1.0.0");
//        final ReleaseId releaseId1 = kieServices.newReleaseId("com", "caicongyang", "1.0.0");
        // 创建初始化的kjar
        InternalKieModule kJar = DroolsUtils.initKieJar(kieServices, releaseId);
        InternalKieModule kJar1 = DroolsUtils.initKieJar(kieServices, releaseId);
        KieRepository repository = kieServices.getRepository();
        repository.addKieModule(kJar);
        repository.addKieModule(kJar1);
        KieContainer kieContainer = kieServices.newKieContainer(releaseId);
        KieContainer kieContainer1 = kieServices.newKieContainer(releaseId);

        //新增一个规则文件
        kJar = DroolsUtils.createKieJar(kieServices, releaseId,
                new ResourceWrapper(ResourceFactory.newByteArrayResource(rules.getBytes()), RULESFILE_NAME));
        repository.addKieModule(kJar);
        kieContainer.updateToVersion(releaseId);
        kJar1 = DroolsUtils.createKieJar(kieServices, releaseId,
                new ResourceWrapper(ResourceFactory.newByteArrayResource(rules1.getBytes()), RULESFILE_NAME));
        repository.addKieModule(kJar1);
        kieContainer1.updateToVersion(releaseId);
        kieContainerMap.put(1,kieContainer);
        kieContainerMap.put(2,kieContainer1);

//        KieSession session = kieContainer.newKieSession();
//        KieSession session1 = kieContainer1.newKieSession();
//
//        sessions.put(1,session);
//        sessions.put(2,session1);
    }

    @Test
    public void testDrools(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kSession = kieContainer.newKieSession("ksession-rules");
        Message message = new Message();
        message.setMessage("Hello World");
        message.setStatus(Message.HELLO);
        kSession.insert(message);
        kSession.fireAllRules();

//        KieResources resources = kieServices.getResources();
//        KieModuleModel kieModuleModel = kieServices.newKieModuleModel();//1
//
//        KieBaseModel baseModel = kieModuleModel.newKieBaseModel(
//                "FileSystemKBase").addPackage("rules");//2
//        baseModel.newKieSessionModel("FileSystemKSession");//3
//        KieFileSystem fileSystem = kieServices.newKieFileSystem();
//
//        String xml = kieModuleModel.toXML();
//        System.out.println(xml);//4
//        fileSystem.writeKModuleXML(xml);//5
//
//        fileSystem.write("src/main/resources/rules/rule.drl", resources
//                .newClassPathResource("kiefilesystem/KieFileSystemTest.drl"));//6
//
//        KieBuilder kb = kieServices.newKieBuilder(fileSystem);
//        kb.buildAll();//7
//        if (kb.getResults().hasMessages(Message.Level.ERROR)) {
//            throw new RuntimeException("Build Errors:\n"
//                    + kb.getResults().toString());
//        }
//        KieContainer kContainer = kieServices.newKieContainer(kieServices
//                .getRepository().getDefaultReleaseId());
//
////        assertNotNull(kContainer.getKieBase("FileSystemKBase"));
//        KieSession kSession = kContainer.newKieSession("FileSystemKSession");
//
//        kSession.fireAllRules();
        System.out.println("-----senond fire end-------");






//        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
//        kbuilder.add(org.drools.io.ResourceFactory.newByteArrayResource(rules.getBytes()), ResourceType.DRL);
//        if (kbuilder.hasErrors()){
//            System.out.println(kbuilder.getErrors().toString());
//        }

        // 创建kbase
//        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
//        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

        // 创建kSession
//        StatefulKnowledgeSession kSession = createKnowledgeSession()
    }
}
