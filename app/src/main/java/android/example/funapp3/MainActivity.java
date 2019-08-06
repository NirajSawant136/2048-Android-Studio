package android.example.funapp3;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView[][]  b = new TextView[4][4];
    ImageButton imageButton;
    TextView win,scoreBoard;
    ConstraintLayout constraintLayout;
    Button up,down,left,right;
    ImageButton strtgame,newgame;
    public static int stop = 0,start = 0,score = 0;
    int i=0,j=0,now=0;
    Random random = new Random (  );
    public List<Integer> strtGme = new ArrayList<> ( 16);
    public List<Integer> strtGme1 = new ArrayList<> ( 16);
    public List<Integer> addblock = new ArrayList<> ( 16);
    public List<Integer> addblock1 = new ArrayList<> ( 2);
    public int[][] status = new int[4][4];
    public int[][] undo = new int[4][4];
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        constraintLayout = (ConstraintLayout)findViewById ( R.id.bck );
        scoreBoard = (TextView)findViewById ( R.id.score );

        b[0][0] = (TextView)findViewById ( R.id.block0 );
        b[0][1] = (TextView)findViewById ( R.id.block1 );
        b[0][2] = (TextView)findViewById ( R.id.block2 );
        b[0][3] = (TextView)findViewById ( R.id.block3 );
        b[1][0] = (TextView)findViewById ( R.id.block4 );
        b[1][1] = (TextView)findViewById ( R.id.block5 );
        b[1][2] = (TextView)findViewById ( R.id.block6 );
        b[1][3] = (TextView)findViewById ( R.id.block7 );
        b[2][0] = (TextView)findViewById ( R.id.block8 );
        b[2][1] = (TextView)findViewById ( R.id.block9 );
        b[2][2] = (TextView)findViewById ( R.id.block10 );
        b[2][3] = (TextView)findViewById ( R.id.block11 );
        b[3][0] = (TextView)findViewById ( R.id.block12 );
        b[3][1] = (TextView)findViewById ( R.id.block13 );
        b[3][2] = (TextView)findViewById ( R.id.block14 );
        b[3][3] = (TextView)findViewById ( R.id.block15 );

        imageButton = (ImageButton)findViewById ( R.id.undo );

        win = (TextView)findViewById ( R.id.winStatus );

        up = (Button)findViewById ( R.id.up );
        down = (Button)findViewById ( R.id.down );
        left = (Button)findViewById ( R.id.left );
        right = (Button)findViewById ( R.id.right );
        strtgame = (ImageButton)findViewById ( R.id.startgame );
        newgame = (ImageButton)findViewById ( R.id.newGame );

        for(i=0;i<4;i++)
        {
            for(j=0;j<4;j++)
            {
                status[i][j] = 0;
            }
        }

        addblock1.add ( 2 );
        addblock1.add ( 2 );
        addblock1.add ( 2 );
        addblock1.add ( 2 );
        addblock1.add ( 4 );

        newgame.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View view ) {
                start = 1;
                NewGame();
            }
        } );
        strtgame.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View view ) {

                StartGame();
            }
        } );

        up.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View view ) {

                if(stop == 0)
                {
                    undoStore ();
                    upAction ( );
                    winCheck ();
                }
            }
        } );
        down.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View view ) {

                if(stop == 0)
                {
                    undoStore ();
                    downAction();
                    winCheck ();
                }
            }
        } );
        left.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View view ) {

                if(stop == 0)
                {
                    undoStore ();
                    leftAction();
                    winCheck ();
                }
            }
        } );
        right.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View view ) {

                if(stop == 0)
                {
                    undoStore ();
                    rightAction();
                    winCheck ();
                }
            }
        } );
        imageButton.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View view ) {

                undoExecute ();
            }
        } );

    }
    public void upAction()
    {
        int m = 1,n = 0,count,allow =0,allow1 = 0;
        for(n=0;n<4;n++)
        {
            count = 0;
            allow = 0;
            while(count != 4)
            {
                for(m=1;m<4-count;m++)
                {
                    if(status[m][n] != 0)
                    {
                        if(status[m-1][n] == status[m][n] && allow == 0)
                        {
                            scoreBoard.setText ( String.valueOf ( Sccore ( status[m][n] ) ) );
                            status[m-1][n] *= 2;
                            b[m-1][n].setText ( String.valueOf ( status[m-1][n] ) );
                            b[m-1][n].setTextColor ( Color.parseColor ( addColor ( m-1,n ) ) );
                            status[m][n] = 0;
                            b[m][n].setText ( "" );
                            b[m][n].setTextColor ( Color.parseColor ( addColor ( m,n ) ) );
                            allow = 1;
                            allow1 = 1;
                        }
                        else if(status[m-1][n] == 0)
                        {
                            status[m-1][n] = status[m][n];
                            b[m-1][n].setText ( String.valueOf ( status[m-1][n] ) );
                            b[m-1][n].setTextColor ( Color.parseColor ( addColor ( m-1,n ) ) );
                            status[m][n] = 0;
                            b[m][n].setText ( "" );
                            b[m][n].setTextColor ( Color.parseColor ( addColor ( m,n ) ) );
                            allow1 = 1;
                        }
                        else
                        {

                        }
                        if(status[1][n] == 0 && count == 0)
                        {
                            if(status[2][n] == status[3][n])
                            {
                                status[2][n] *= 2;
                                b[2][n].setText ( String.valueOf ( status[2][n] ) );
                                b[2][n].setTextColor ( Color.parseColor ( addColor ( 2,n ) ) );
                                status[3][n] = 0;
                                b[3][n].setText ( String.valueOf ( "" ) );
                                b[3][n].setTextColor ( Color.parseColor ( addColor ( 2,n ) ) );
                            }
                        }
                    }
                }
                count += 1;
            }
        }
        if(allow1 == 1)
            addBlock ();
    }
    public void downAction()
    {
        int m = 3,n = 0,count,allow =0,allow1 = 0;
        for(n=0;n<4;n++)
        {
            count = 0;
            allow = 0;
            while(count != 4)
            {
                for(m=2;m>=count;m--)
                {
                    if(status[m][n] != 0)
                    {
                        if(status[m+1][n] == status[m][n] && allow == 0)
                        {
                            scoreBoard.setText ( String.valueOf ( Sccore ( status[m][n] ) ) );
                            status[m+1][n] *= 2;
                            b[m+1][n].setText ( String.valueOf ( status[m+1][n] ) );
                            b[m+1][n].setTextColor ( Color.parseColor ( addColor ( m+1,n ) ) );
                            status[m][n] = 0;
                            b[m][n].setText ( "" );
                            b[m][n].setTextColor ( Color.parseColor ( addColor ( m,n ) ) );
                            allow = 1;
                            allow1 = 1;
                        }
                        else if(status[m+1][n] == 0)
                        {
                            status[m+1][n] = status[m][n];
                            b[m+1][n].setText ( String.valueOf ( status[m+1][n] ) );
                            b[m+1][n].setTextColor ( Color.parseColor ( addColor ( m+1,n ) ) );
                            status[m][n] = 0;
                            b[m][n].setText ( "" );
                            b[m][n].setTextColor ( Color.parseColor ( addColor ( m,n ) ) );
                            allow1 = 1;
                        }
                        if(status[2][n] == 0 && count == 0)
                        {
                            if(status[1][n] == status[0][n])
                            {
                                status[0][n] *= 2;
                                b[0][n].setText ( String.valueOf ( status[0][n] ) );
                                b[0][n].setTextColor ( Color.parseColor ( addColor ( 0,n ) ) );
                                status[1][n] = 0;
                                b[1][n].setText ( String.valueOf ( "" ) );
                                b[1][n].setTextColor ( Color.parseColor ( addColor ( 1,n ) ) );
                            }
                        }
                    }
                }
                count += 1;
            }
        }
        if(allow1 == 1)
            addBlock ();
    }
    public void leftAction()
    {
        int m = 1,n = 0,count,allow =0,allow1 = 0;
        for(m=0;m<4;m++)
        {
            count =0;
            allow = 0;
            while(count != 4)
            {
                for(n=1;n<4-count;n++)
                {
                    if(status[m][n] != 0)
                    {
                        if(status[m][n-1] == status[m][n] && allow == 0)
                        {
                            scoreBoard.setText ( String.valueOf ( Sccore ( status[m][n] ) ) );
                            status[m][n-1] *= 2;
                            b[m][n-1].setText ( String.valueOf ( status[m][n-1] ) );
                            b[m][n-1].setTextColor ( Color.parseColor ( addColor ( m,n-1 ) ) );
                            status[m][n] = 0;
                            b[m][n].setText ( "" );
                            b[m][n].setTextColor ( Color.parseColor ( addColor ( m,n ) ) );
                            allow = 1;
                            allow1 = 1;
                        }
                        else if(status[m][n-1] == 0)
                        {
                            status[m][n-1] = status[m][n];
                            b[m][n-1].setText ( String.valueOf ( status[m][n-1] ) );
                            b[m][n-1].setTextColor ( Color.parseColor ( addColor ( m,n-1 ) ) );
                            status[m][n] = 0;
                            b[m][n].setText ( "" );
                            b[m][n].setTextColor ( Color.parseColor ( addColor ( m,n ) ) );
                            allow1 = 1;
                        }
                        if(status[m][1] == 0 && count == 0)
                        {
                            if(status[m][2] == status[m][3])
                            {
                                status[m][2] *= 2;
                                b[m][2].setText ( String.valueOf ( status[m][2] ) );
                                b[m][2].setTextColor ( Color.parseColor ( addColor ( m,2 ) ) );
                                status[m][3] = 0;
                                b[m][3].setText ( String.valueOf ( "" ) );
                                b[m][3].setTextColor ( Color.parseColor ( addColor ( m,3 ) ) );
                            }
                        }
                    }
                }
                count += 1;
            }
        }
        if(allow1 == 1)
            addBlock ();
    }
    public void rightAction()
    {
        int m = 3,n = 0,count,allow =0,allow1 = 0;
        for(m=0;m<4;m++)
        {
            count = 0;
            allow = 0;
            while(count != 4)
            {
                for(n=2;n>=count;n--)
                {
                    if(status[m][n] != 0)
                    {
                        if(status[m][n+1] == status[m][n] && allow == 0)
                        {
                            scoreBoard.setText ( String.valueOf ( Sccore ( status[m][n] ) ) );
                            status[m][n+1] *= 2;
                            b[m][n+1].setText ( String.valueOf ( status[m][n+1] ) );
                            b[m][n+1].setTextColor ( Color.parseColor ( addColor ( m,n+1 ) ) );
                            status[m][n] = 0;
                            b[m][n].setText ( "" );
                            b[m][n].setTextColor ( Color.parseColor ( addColor ( m,n ) ) );
                            allow = 1;
                            allow1 = 1;
                        }
                        else if(status[m][n+1] == 0)
                        {
                            status[m][n+1] = status[m][n];
                            b[m][n+1].setText ( String.valueOf ( status[m][n+1] ) );
                            b[m][n+1].setTextColor ( Color.parseColor ( addColor ( m,n+1 ) ) );
                            status[m][n] = 0;
                            b[m][n].setText ( "" );
                            b[m][n].setTextColor ( Color.parseColor ( addColor ( m,n ) ) );
                            allow1 = 1;
                        }
                        if(status[m][2] == 0 && count == 0)
                        {
                            if(status[m][1] == status[m][0])
                            {
                                status[m][0] *= 2;
                                b[m][0].setText ( String.valueOf ( status[m][0] ) );
                                b[m][0].setTextColor ( Color.parseColor ( addColor ( m,0 ) ) );
                                status[m][1] = 0;
                                b[m][1].setText ( String.valueOf ( "" ) );
                                b[m][1].setTextColor ( Color.parseColor ( addColor ( m,1 ) ) );
                            }
                        }
                    }
                }
                count += 1;
            }
        }
        if(allow1 == 1)
            addBlock ();
    }
    public void addBlock()
    {
        int x,y,value;
        double d;
        addblock.clear ();
        for(x=0;x<4;x++)
        {
            for(y=0;y<4;y++)
            {
                if(status[x][y] == 0)
                {
                    value = 4*x + y;
                    addblock.add ( value );
                }
            }
        }
        int val = addblock.get ( random.nextInt (addblock.size ()) );
        int val1 = addblock1.get ( random.nextInt (addblock1.size ()) );
        for(y=0;y<4;y++)
            if((val - y)%4 == 0)
            {
                d = (val - y)/4;
                x = (int)d;
                status[x][y] = val1;
                b[x][y].setText ( String.valueOf ( status[x][y] ) );
                b[x][y].setTextColor ( Color.parseColor ( addColor ( x,y ) ) );
                break;
            }
    }
    public void StartGame()
    {
        int firstBlock,firstBlock1;
        if(start == 0)
        {
            for (int k = 0; k < 4; k++)
            {
                    strtGme.add ( k );
            }
            firstBlock = strtGme.get ( random.nextInt (strtGme.size ()) );
            firstBlock1 = strtGme.get ( random.nextInt (strtGme.size ()) );
            b[firstBlock][firstBlock1].setText ( "2" );
            status[firstBlock][firstBlock1] = 2;
            b[firstBlock][firstBlock1].setTextColor ( Color.parseColor ( addColor ( firstBlock,firstBlock1 ) ) );
            int m = 0,n=0;
            for(m=0;m<4;m++)
            {
                for(n=0;n<4;n++)
                {
                    if ( status[m][n] == 0 )
                    {
                        strtGme1.add ( m );
                    }
                }
            }
            firstBlock = strtGme1.get ( random.nextInt (strtGme.size ()) );
            firstBlock1 = strtGme1.get ( random.nextInt (strtGme.size ()) );
            b[firstBlock][firstBlock1].setText ( "2" );
            status[firstBlock][firstBlock1] = 2;
            b[firstBlock][firstBlock1].setTextColor ( Color.parseColor ( addColor ( firstBlock,firstBlock1 ) ) );
        }
        start = 1;
    }
    public void NewGame()
    {
        start = 0;
        stop = 0;
        Intent intent = new Intent ( this,MainActivity.class );
        startActivity ( intent );
        finish ();
    }
    public void winCheck()
    {
        for(int u=0;u<4;u++)
        {
            for(int v=0;v<4;v++)
            {
                if(status[u][v] == 2048)
                {
                    win.setText ( "YOU  WIN" );
                    stop = 1;
                    constraintLayout.setAlpha ( 0.6f );
                    win.setAlpha ( 1.0f );
                }
            }
        }
        int GameOver = 0;
        for(int u=0;u<4;u++)
        {
            for(int v=0;v<4;v++)
            {
                if(status[u][v] != 0)
                    GameOver += 1;
            }
        }
        if(GameOver == 16) {
            GameOver =0;
            for (int v = 1; v < 3; v++)
            {
                if ( status[0][v] != status[0][v - 1] && status[0][v] != status[0][v + 1] && status[0][v] == status[1][v] ) {
                    GameOver += 1;
                }
                if ( status[3][v] != status[3][v - 1] && status[3][v] != status[3][v + 1] && status[3][v] == status[2][v] ) {
                    GameOver += 1;
                }
                if ( status[v][0] != status[v-1][0] && status[v][0] != status[v+1][0] && status[v][0] != status[v][1] ) {
                    GameOver += 1;
                }
                if ( status[v][3] != status[v-1][3] && status[v][3] != status[v+1][3] && status[v][3] != status[v][2] ) {
                    GameOver += 1;
                }
            }
            if(status[0][0] != status[0][1] && status[0][0] != status[1][0])
                GameOver += 1;
            if(status[0][3] != status[0][2] && status[0][3] != status[1][3])
                GameOver += 1;
            if(status[3][0] != status[3][1] && status[3][0] != status[2][0])
                GameOver += 1;
            if(status[3][3] != status[3][2] && status[3][3] != status[2][3])
                GameOver += 1;
            for(int u=1;u<3;u++)
            {
                for(int v=1;v<3;v++)
                {
                    if(status[u][v] != status[u][v+1] && status[u][v] != status[u][v-1] && status[u][v] != status[u+1][v] && status[u][v] != status[u-1][v])
                        GameOver += 1;
                }
            }
            if(GameOver == 12)
            {
                win.setText ( "GAME  OVER" );
                stop = 1;
                constraintLayout.setAlpha ( 0.6f );
                win.setAlpha ( 1.0f );
            }
        }
    }
    public String addColor(int a,int b)
    {
        String color = "#00000000";
        if(status[a][b] == 2)
            color = "#f7ef12";
        else if(status[a][b] == 4)
            color = "#f76e12";
        else if(status[a][b] == 8)
            color = "#2f57e9" ;
        else if(status[a][b] == 16)
            color = "#20bbd3" ;
        else if(status[a][b] == 32)
            color = "#d01c85" ;
        else if(status[a][b] == 64)
            color = "#09ce26" ;
        else if(status[a][b] == 128)
            color =  "#7713a5" ;
        else if(status[a][b] == 256)
             color = "#11dfa8" ;
        else if(status[a][b] == 512)
              color =  "#3e39c9" ;
        else if(status[a][b] == 1024)
               color = "#e74149" ;
        else if(status[a][b] == 2048)
             color = "#ffffff";
        return color;
    }
    public int Sccore(int a)
    {
        switch(a)
        {
            case 2:score += 5;break;
            case 4:score += 10;break;
            case 8:score += 15;break;
            case 16:score += 20;break;
            case 32:score += 25;break;
            case 64:score += 30;break;
            case 128:score += 35;break;
            case 256:score += 40;break;
            case 512:score += 45;break;
            case 1024:score += 50;break;
            case 2048:score += 60;break;
        }
        return score;
    }
    public void undoStore()
    {
        for(int e=0;e<4;e++)
        {
            for(int f=0;f<4;f++)
            {
                undo[e][f] = status[e][f];
            }
        }
    }
    public void undoExecute()
    {
        for(int e=0;e<4;e++)
        {
            for(int f=0;f<4;f++)
            {
                status[e][f] = undo[e][f];
                b[e][f].setText ( String.valueOf ( status[e][f] ) );
                b[e][f].setTextColor ( Color.parseColor ( addColor ( e,f ) ) );
            }
        }
        win.setText ( "" );
        stop = 0;
        constraintLayout.setAlpha ( 1.0f );
        win.setAlpha ( 1.0f );
    }
}
