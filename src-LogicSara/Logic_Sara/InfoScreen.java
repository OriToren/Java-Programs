package Logic_Sara;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoScreen extends JPanel {
    CardLayout screensaver;
    MainPanel mainpanel;
    Boolean does_screen2_exist;
    InfoScreen(CardLayout screensaver,MainPanel mainpanel,String statement,Boolean does_screen2_exist){
        this.mainpanel=mainpanel;
        this.does_screen2_exist=does_screen2_exist;
        this.screensaver=screensaver;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1200, 900));
        JPanel southpanel=new JPanel();
        southpanel.setLayout(new GridLayout(1,2));
        JButton backtoscreen1=new JButton("back to keyboard screen");
        backtoscreen1.setFont(new Font("Cambria",Font.BOLD,35));
        backtoscreen1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screensaver.show(mainpanel,"screen_1");
            }
        });
        southpanel.add(backtoscreen1);
        JButton backtoscreen2=new JButton("back to function screen");
        backtoscreen2.setFont(new Font("Cambria",Font.BOLD,35));
        backtoscreen2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (does_screen2_exist){
                    screensaver.show(mainpanel,"screen_2");
                }
            }
        });
        southpanel.add(backtoscreen2);
        southpanel.setPreferredSize(new Dimension(100,100));
        JTextArea infoarea=new JTextArea();
        infoarea.setEditable(false);
        infoarea.setLineWrap(true);
        infoarea.setFont(new Font("Cambria",Font.PLAIN,10));
        infoarea.setText("""
       WELCOME TO LOGIC SARA 
       =====================
       
       PART 0: INTRODUCTION
       --------------------
       This is a kind of calculator, but it works with Boolean Algebra and doesn't really 
       calculate numbers on the spot. My teacher for Logic in Computer Science suggested we 
       build this to understand the logic (the app is named after her). I did it and it was 
       very enjoyable, so I wanted to take it a step further into something more usable for 
       the average person. It helped me learn a lot, and the fact that you're here reading 
       this is also very nice! 
       
       Now let's start. What is even Boolean Algebra?
       
       
       PART 1: WHAT IS BOOLEAN ALGEBRA?
       --------------------------------
       In normal math, variables (x, y) are numbers.
       In Boolean Logic, variables are "Statements" that are either:
       TRUE (T) or FALSE (F).
       
       We combine these simple statements to build complex logic, just like
       computer processors do!
       
       For example, a statement like "Every day has a morning and an evening" is obviously 
       true, thus it gets the True value.
       
       A lot of the time, unlike the example I just gave, these statements won't have a set 
       truth, and will change depending on operators the user uses (I call them "Deciders"—
       you'll see what that is later).
       The main thing you need to understand is that it's math with Statements, and every 
       Statement at the end of the day will be either False or True.
       
       
       PART 2: THE LANGUAGE
       --------------------
       1. THE ATOMS (The Variables)
          We use the letter 'p' followed by a number (e.g., p1, p2).
          Think of p1 as "It is raining". It is either True or False (as I mentioned before, 
          they can be neither true nor false depending on the Decider). 
          
          Remember, atoms are the building blocks of statements. But every combination of 
          atoms with operators is also a statement. So for example:
          p1, p1 v p2, p1 v p2 v p3 v p4 v p5 are all statements.
          Two atoms are the same if they have the same number after them.

       2. THE OPERATORS (The Rules)
          ~  (NOT)
             The Opposite. If p1 is True, ~p1 is False. Write it before any Statement, 
             not just a simple atom, to reverse its truth.
             
          v  (OR)
             The result is True if AT LEAST ONE side is True.
             (p1 v p2) is True if p1 is True, p2 is True, or both.
             To use this you can just type 'or' as well instead of the sign.
             
          ∧  (AND)
             The Strict One. The result is True only if BOTH sides are True.
             Likewise, you can just type 'and' and it will work.
             
          -> (IMPLIES)
             The Promise. "If p1 happens, then p2 must happen."
             This is only False if p1 happens (True) but p2 fails (False).
             
          <-> (IFF - If And Only If)
             The Mirror. True only if both sides are exactly the same.
             
          There are more operators that exist, but all of them can be created as a result 
          of these 5. For example, the operator XOR is just the NOT of IFF: ~(p1 <-> p2).

          With these tools, you can craft your own statements in the keyboard screen, as well 
          as delete them (Whoa!!! So exciting!) and go to this menu—though I guess you know 
          that if you're reading this.
          
          Only valid statements can have stuff done on them. The Function Screen is where 
          this stuff is done. What is a valid statement?
          - Operators need statements between them (or in case of NOT, to the right of it).
          - Atoms need numbers after them.
          - Proper syntax (all necessary fixes will be printed in the box below your textbox).


       PART 3: ANALYSIS TOOLS (Function Screen)
       ----------------------------------------
       Once you enter a statement, LogicSara offers unique tools:
       
       [ QUICK-CHECK PRESETS ] (Deciders)
       Instead of checking every possibility, we can run fast tests:
       * All True:  What happens if every p(x) is True?
       * All False: What happens if every p(x) is False?
       * Evens True: Sets p2, p4, p6... to True, and odds to False.
       * Odds True:  Sets p1, p3, p5... to True, and evens to False.
       
       These are Deciders, which allow you to control the truth of your atoms.
       You can also create your own Decider, and decide yourself which atoms are true.
       
       [ TRUTH TABLE ]
       Generates a map of EVERY possible combination of T/F for your atoms.
       This shows you the "DNA" of your statement.
       Basically shows the answers to every possible Decider you can apply on the statement. 
       (There are 2^n Deciders for n atoms).
       
       [ TAUTOLOGY CHECK ]
       Checks if your statement is a "Universal Truth."
       Does it output True in every single possible scenario?
       Basically, will it always be true regardless of the Decider?
       
       For example: p1 v ~p1 is always true since OR checks if at least one is true. 
       If p1 is true then the statement is true; if p1 is false then ~p1 is true and 
       the statement is still true.
       
       [ CONTRADICTION CHECK ]
       Checks if your statement is "Always False."
       It is like the last check but the opposite.
       
       [ COMPARE ]
       Allows you to check 2 statements and see if they are equal.
       Two statements are equal if their Truth Tables are the same. Basically, if for 
       every possible Decider, both statements result in the same truth value.
       
       Thank you for reading and using this little project! I hope it helps :)
       """);
        infoarea.setFont(new Font("Cambria",Font.PLAIN,20));
        JScrollPane middleofinfo=new JScrollPane(infoarea);
        this.add(middleofinfo,BorderLayout.CENTER);
        this.add(southpanel,BorderLayout.SOUTH);

    }

}
