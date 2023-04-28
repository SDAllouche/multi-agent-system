package ma.enset.agents;

import jade.core.AID;
import jade.core.behaviours.*;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

public class Client extends GuiAgent {

    private ClientGui clientGui;

    @Override
    protected void setup() {

        clientGui= (ClientGui) getArguments()[0];

        System.out.println("********** Agent Initialisation **********");
        ParallelBehaviour parallelBehaviour=new ParallelBehaviour();
        addBehaviour(parallelBehaviour);

        clientGui.setClient(this);


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

        /*parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                System.out.println("********** Cyclic Behaviour Action **********");
            }
        });

        parallelBehaviour.addSubBehaviour(new TickerBehaviour(this,1000) {
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
        ACLMessage message=new ACLMessage(ACLMessage.REQUEST);
        message.addReceiver(new AID("Server",AID.ISLOCALNAME));
        message.setContent(guiEvent.getParameter(0).toString());
        send(message);
    }
}
