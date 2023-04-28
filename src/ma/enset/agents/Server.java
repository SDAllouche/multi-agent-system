package ma.enset.agents;

import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

public class Server extends GuiAgent {

    private ServerGui serverGui;

    @Override
    protected void setup() {

        System.out.println("********** Agent Initialisation **********");
        ParallelBehaviour parallelBehaviour=new ParallelBehaviour();
        addBehaviour(parallelBehaviour);
        serverGui = (ServerGui) getArguments()[0];

        /*addBehaviour(new Behaviour() {
            private int cpt=0;
            @Override
            public void action() {
                cpt++;
                System.out.println("Action : "+cpt);
            }

            @Override
            public boolean done() {
                if (cpt==100) return true;
                return false;
            }
        });*/

        /*addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.println("********** One Shot Behaviour Action **********");
            }
        });*/

        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                System.out.println("********** Cyclic Behaviour Action **********");
                ACLMessage meesageRequest=receive();
                if (meesageRequest != null) {
                    serverGui.showMessage(meesageRequest.getContent());
                } else{
                    block();
                }
            }
        });

        /*parallelBehaviour.addSubBehaviour(new TickerBehaviour(this,1000) {
            @Override
            protected void onTick() {
                System.out.println("********** Ticker Behaviour Action **********");
            }
        });*/
    }

    @Override
    protected void takeDown() {
        System.out.println("********** Take Down **********");
    }

    @Override
    protected void beforeMove() {
        System.out.println("********** Before Move **********");
    }

    @Override
    protected void afterMove() {
        System.out.println("********** After move **********");
    }

    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {

    }
}
