package com.ezi.larbianceur.esigym.suppliment;

import java.util.ArrayList;

/**
 * Created by Oclemy on 9/7/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class Paginator {

    public  int TOTAL_NUM_ITEMS;
    public  int ITEMS_PER_PAGE;
    public  int ITEMS_REMAINING;
    public  int LAST_PAGE;
    int startItem = 0;

    public Paginator(int TOTAL_NUM_ITEMS, int ITEMS_PER_PAGE) {
        this.TOTAL_NUM_ITEMS = TOTAL_NUM_ITEMS;
        this.ITEMS_PER_PAGE = ITEMS_PER_PAGE;
        this.ITEMS_REMAINING =TOTAL_NUM_ITEMS % ITEMS_PER_PAGE;
        this.LAST_PAGE = TOTAL_NUM_ITEMS/ITEMS_PER_PAGE ;
    }




    public ArrayList<Product> generatePage(int currentPage,ArrayList<Product> products)
    {
      startItem  =currentPage*ITEMS_PER_PAGE;
        int numOfData=ITEMS_PER_PAGE;

        ArrayList<Product> pageData=new ArrayList<>();



        if (currentPage==LAST_PAGE && ITEMS_REMAINING>0)
        {
            for (int i=startItem;i<startItem+ITEMS_REMAINING;i++)
            {
                pageData.add(products.get( i ));
            }
        }else
        {
            for (int i=startItem;i<startItem+numOfData;i++)
            {
                pageData.add(products.get( i ));
            }
        }


        return pageData;
    }
}
