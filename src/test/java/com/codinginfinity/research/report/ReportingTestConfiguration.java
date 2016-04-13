package com.codinginfinity.research.report;

import com.codinginfinity.research.report.defaultImpl.PublicationType;
import com.codinginfinity.research.people.ReqEntity;
import com.codinginfinity.research.report.defaultImpl.ReportingImpl;
import com.codinginfinity.research.report.defaultImpl.GetProgressReportRequest;
import com.codinginfinity.research.report.defaultImpl.GetAccreditationUnitReportRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author COS301 Reporting Alpha Team
 */
@Configuration
class ReportingTestConfiguration {
    @Bean 
    public ReqEntity groupEntity(){
        return new ReqEntity("GROUP", "Test Research Group 1");
    
    }
       
    @Bean 
    public ReqEntity personEntity(){
        return new ReqEntity("PERSON", "Paul");
    
    }
    
   @Bean
    public PublicationType PubType(){
        return new PublicationType("Journal", 1.0);
    }
   /* @Bean
    public ReportingImpl ReportingMock(){        
        return new ReportingImpl();
    }*/
    
    /**
     * 
     * @return 
     */
   /* @Bean
    public GetAccreditationUnitReportRequest ErrorAccUnitReportRequestMock(){        
        return new GetAccreditationUnitReportRequest();
    }*/
    
    //============================================================================================
    //============================================================================================
    
    /**
     * 
     * @return 
     */
   /* @Bean
    public GetProgressReportRequest ErrorProgressReportRequestMock(){        
        return new GetProgressReportRequest();
    }
    */
    /**
     * 
     * @return 
     */
   /* @Bean
    public GetProgressReportRequest PersonEntityProgRequestMock()
    {
        ReqEntity e  = new ReqEntity("PERSON", "Dave");
               
        return new GetProgressReportRequest(e);
    }
    
    @Bean
    public GetProgressReportRequest GroupEntityProgRequestMock()
    {
        ReqEntity e  = new ReqEntity("GROUP", "Test Research Group 1");
               
        return new GetProgressReportRequest(e);
    }
    
    @Bean
    public GetProgressReportRequest PubTypeProgRequestMock()
    {
        PublicationType t = new PublicationType();
        t.setName("Journal");
        t.setAccreditationPoints(1.0); 
               
        return new GetProgressReportRequest(t);
    }
    
    @Bean
    public GetProgressReportRequest GroupTypeProgRequestMock()
    {
        ReqEntity e  = new ReqEntity("GROUP", "Test Research Group 2");
        PublicationType t = new PublicationType();
        t.setName("Journal");
        t.setAccreditationPoints(1.0); 
               
        return new GetProgressReportRequest(e,t);
    }
    
     @Bean
     public GetProgressReportRequest PersonTypeProgRequestMock()
    {
        ReqEntity e  = new ReqEntity("PERSON", "sally");
        PublicationType t = new PublicationType();
        t.setName("Journal");
        t.setAccreditationPoints(1.0); 
               
        return new GetProgressReportRequest(e,t);
    }
    */

    //--------------------------ACCREDITATION UNIT MOCK OBJECTS--------------------------\\

    ///////////////////////////////////PERSON ENTITY/////////////////////////////////////
    /**
     * 
     * @return Person Entity Mock Object
     */
    /*@Bean
    public GetAccreditationUnitReportRequest aPersonEntityRequestMock1()
    {
        ReqEntity e  = new ReqEntity("PERSON", "Dave");
      return new GetAccreditationUnitReportRequest(e, null, null, null);
      
    }
*/
    /**
     *
     *
     * @return AccreditationUnit Person and Period Mock object
     */
    
    /*
    @Bean
    public GetAccreditationUnitReportRequest aPersonPeriodRequestMock()
    {
        ReqEntity e = new ReqEntity("Person", "Sally");
        SimpleDateFormat tmp = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try
        {
            start = tmp.parse("2015-01-01");
            end = tmp.parse("2017-01-01");
        } catch (ParseException e1)
        {
            e1.printStackTrace();
        }
        Period p = new Period();
        p.setStartDate(start);
        p.setEndDate(end);

        return new GetAccreditationUnitReportRequest(e, null, null, p);
    }
*/
    /**
     *
     * @return AccreditationUnit Person and State Mock object
     */
    /*@Bean
    public GetAccreditationUnitReportRequest aPersonStateRequestMock()
    {
        ReqEntity e = new ReqEntity("Person", "Paul");
        LifeCycleState state = new LifeCycleState();
        state.setLifeCycleState("InProgress");
        return new GetAccreditationUnitReportRequest(e, state, null, null);
    }
*/
    /**
     *
     * @return AccreditationUnit Person and Type Mock object
     */
   /* @Bean
    public GetAccreditationUnitReportRequest aPersonTypeRequestMock()
    {
        ReqEntity e = new ReqEntity("Person", "Sally");
        PublicationType type = new PublicationType();
        type.setName("High-Impact Journal");
        return new GetAccreditationUnitReportRequest(e, null, type, null);
    }*/

    /**
     *
     * @return AccreditationUnit Person,State and Type Mock object
     */
    /*@Bean
    public GetAccreditationUnitReportRequest aPersonStateTypeRequestMock()
    {
        ReqEntity e = new ReqEntity("Person", "Paul");
        PublicationType type = new PublicationType();
        type.setName("Accredited Book");
        LifeCycleState state = new LifeCycleState();
        state.setLifeCycleState("Published");
        return new GetAccreditationUnitReportRequest(e, state, type, null);
    }
*/
    /**
     *
     * @return AccreditationUnit Person,State and Period Mock object
     */
  /*  @Bean
    public GetAccreditationUnitReportRequest aPersonStatePeriodRequestMock()
    {
        ReqEntity e = new ReqEntity("Person", "Dave");
        LifeCycleState state = new LifeCycleState();
        state.setLifeCycleState("Published");

        SimpleDateFormat tmp = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try
        {
            start = tmp.parse("2015-06-04");
            end = tmp.parse("2016-10-11");
        } catch (ParseException e1)
        {
            e1.printStackTrace();
        }
        Period p = new Period();
        p.setStartDate(start);
        p.setEndDate(end);

        return new GetAccreditationUnitReportRequest(e, state, null, p);
    }
*/
    /**
     *
     * @return AccreditationUnit Person,Type and Period Mock object
     */
  /*  @Bean
    public GetAccreditationUnitReportRequest aPersonTypePeriodRequestMock()
    {
        ReqEntity e = new ReqEntity("Person", "Dave");
        PublicationType type = new PublicationType();
        type.setName("High-Impact Journal");


        SimpleDateFormat tmp = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try
        {
            start = tmp.parse("2013-12-24");
            end = tmp.parse("2016-04-12");
        } catch (ParseException e1)
        {
            e1.printStackTrace();
        }
        Period p = new Period();
        p.setStartDate(start);
        p.setEndDate(end);

        return new GetAccreditationUnitReportRequest(e, null, type, p);
    }
*/
    /**
     *
     * @return AccreditationUnit Person,State,Type and Period Mock object
     */
  /*  @Bean
    public GetAccreditationUnitReportRequest aPersonStateTypePeriodRequestMock()
    {
        ReqEntity e = new ReqEntity("Person", "Sally");
        PublicationType type = new PublicationType();
        type.setName("High-Impact Journal");
        LifeCycleState state = new LifeCycleState();
        state.setLifeCycleState("InProgress");

        SimpleDateFormat tmp = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try
        {
            start = tmp.parse("2015-07-12");
            end = tmp.parse("2016-08-20");
        } catch (ParseException e1)
        {
            e1.printStackTrace();
        }
        Period p = new Period();
        p.setStartDate(start);
        p.setEndDate(end);

        return new GetAccreditationUnitReportRequest(e, state, type, p);
    }
*/
    /////////////////////////////////////Group Entity/////////////////////////////////////////

    /**
     *
     * @return Group Mock object
     */
  /*  @Bean
    public GetAccreditationUnitReportRequest aGroupEntityRequestMock1()
    {
        ReqEntity e  = new ReqEntity("Group", "Test Research Group 1");
        return new GetAccreditationUnitReportRequest(e, null, null, null);

    }
*/
    /**
     *
     *
     * @return AccreditationUnit Group and Period Mock object
     */
/*    @Bean
    public GetAccreditationUnitReportRequest aGroupPeriodRequestMock()
    {
        ReqEntity e = new ReqEntity("Group", "Test Research Group 2");
        SimpleDateFormat tmp = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try
        {
            start = tmp.parse("2016-01-01");
            end = tmp.parse("2016-05-01");
        } catch (ParseException e1)
        {
            e1.printStackTrace();
        }
        Period p = new Period();
        p.setStartDate(start);
        p.setEndDate(end);

        return new GetAccreditationUnitReportRequest(e, null, null, p);
    }
*/
    /**
     *
     * @return AccreditationUnit Group and State Mock object
     */
  /*  @Bean
    public GetAccreditationUnitReportRequest aGroupStateRequestMock()
    {
        ReqEntity e = new ReqEntity("Group", "Test Research Group 1");
        LifeCycleState state = new LifeCycleState();
        state.setLifeCycleState("Published");
        return new GetAccreditationUnitReportRequest(e, state, null, null);
    }
*/
    /**
     *
     * @return AccreditationUnit Group and Type Mock object
     */
  /*  @Bean
    public GetAccreditationUnitReportRequest aGroupTypeRequestMock()
    {
        ReqEntity e = new ReqEntity("Group", "Test Research Group 2");
        PublicationType type = new PublicationType();
        type.setName("High-Impact Journal");
        return new GetAccreditationUnitReportRequest(e, null, type, null);
    }
*/
    /**
     *
     * @return AccreditationUnit Group,State and Type Mock object
     */
  /*  @Bean
    public GetAccreditationUnitReportRequest aGroupStateTypeRequestMock()
    {
        ReqEntity e = new ReqEntity("Group", "Test Research Group 1");
        PublicationType type = new PublicationType();
        type.setName("High-Impact Journal");
        LifeCycleState state = new LifeCycleState();
        state.setLifeCycleState("Published");
        return new GetAccreditationUnitReportRequest(e, state, type, null);
    }
*/
    /**
     *
     * @return AccreditationUnit Group,State and Period Mock object
     */
  /*  @Bean
    public GetAccreditationUnitReportRequest aGroupStatePeriodRequestMock()
    {
        ReqEntity e = new ReqEntity("Group", "Test Research Group 2");
        LifeCycleState state = new LifeCycleState();
        state.setLifeCycleState("InProgress");

        SimpleDateFormat tmp = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try
        {
            start = tmp.parse("2012-12-04");
            end = tmp.parse("2015-11-11");
        } catch (ParseException e1)
        {
            e1.printStackTrace();
        }
        Period p = new Period();
        p.setStartDate(start);
        p.setEndDate(end);

        return new GetAccreditationUnitReportRequest(e, state, null, p);
    }
*/
    /**
     *
     * @return AccreditationUnit Group,Type and Period Mock object
     */
  /*  @Bean
    public GetAccreditationUnitReportRequest aGroupTypePeriodRequestMock()
    {
        ReqEntity e = new ReqEntity("Group", "Test Research Group 1");
        PublicationType type = new PublicationType();
        type.setName("Accredited Journal");


        SimpleDateFormat tmp = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try
        {
            start = tmp.parse("2015-10-20");
            end = tmp.parse("2016-03-15");
        } catch (ParseException e1)
        {
            e1.printStackTrace();
        }
        Period p = new Period();
        p.setStartDate(start);
        p.setEndDate(end);

        return new GetAccreditationUnitReportRequest(e, null, type, p);
    }
*/
    /**
     *
     * @return AccreditationUnit Group,State,Type and Period Mock object
     */
  /*  @Bean
    public GetAccreditationUnitReportRequest aGroupStateTypePeriodRequestMock()
    {
        ReqEntity e = new ReqEntity("Group", "Sally");
        PublicationType type = new PublicationType();
        type.setName("Accredited Journal");
        LifeCycleState state = new LifeCycleState();
        state.setLifeCycleState("InProgress");

        SimpleDateFormat tmp = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try
        {
            start = tmp.parse("2015-09-16");
            end = tmp.parse("2016-10-10");
        } catch (ParseException e1)
        {
            e1.printStackTrace();
        }
        Period p = new Period();
        p.setStartDate(start);
        p.setEndDate(end);

        return new GetAccreditationUnitReportRequest(e, state, type, p);
    }
*/
    ///////////////////////////////////No Entity//////////////////////////////////////

    /**
     * @return null mock object
     */
  /*  @Bean
    public GetAccreditationUnitReportRequest aNullRequestMock()
    {
        return new GetAccreditationUnitReportRequest(null, null, null, null);
    }
*/

    /**
     *
     *
     * @return AccreditationUnit Period Mock object
     */
  /*  @Bean
    public GetAccreditationUnitReportRequest aPeriodRequestMock()
    {
        SimpleDateFormat tmp = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try
        {
            start = tmp.parse("2014-11-01");
            end = tmp.parse("2016-07-21");
        } catch (ParseException e1)
        {
            e1.printStackTrace();
        }
        Period p = new Period();
        p.setStartDate(start);
        p.setEndDate(end);

        return new GetAccreditationUnitReportRequest(null, null, null, p);
    }
*/
    /**
     *
     * @return AccreditationUnit State Mock object
     */
  /*  @Bean
    public GetAccreditationUnitReportRequest aStateRequestMock()
    {
        LifeCycleState state = new LifeCycleState();
        state.setLifeCycleState("Published");
        return new GetAccreditationUnitReportRequest(null,state, null, null);
    }
*/
    /**
     *
     * @return AccreditationUnit Type Mock object
     */
  /*  @Bean
    public GetAccreditationUnitReportRequest aTypeRequestMock()
    {
        PublicationType type = new PublicationType();
        type.setName("High-Impact Journal");
        return new GetAccreditationUnitReportRequest(null, null, type, null);
    }
*/
    /**
     *
     * @return AccreditationUnit State and Type Mock object
     */
  /*  @Bean
    public GetAccreditationUnitReportRequest aStateTypeRequestMock()
    {
        PublicationType type = new PublicationType();
        type.setName("High-Impact Journal");
        LifeCycleState state = new LifeCycleState();
        state.setLifeCycleState("Published");
        return new GetAccreditationUnitReportRequest(null, state, type, null);
    }
*/
    /**
     *
     * @return AccreditationUnit State and Period Mock object
     */
  /*  @Bean
    public GetAccreditationUnitReportRequest aStatePeriodRequestMock()
    {
        LifeCycleState state = new LifeCycleState();
        state.setLifeCycleState("InProgress");

        SimpleDateFormat tmp = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try
        {
            start = tmp.parse("2015-08-04");
            end = tmp.parse("2016-03-07");
        } catch (ParseException e1)
        {
            e1.printStackTrace();
        }
        Period p = new Period();
        p.setStartDate(start);
        p.setEndDate(end);

        return new GetAccreditationUnitReportRequest(null, state, null, p);
    }
*/
    /**
     *
     * @return AccreditationUnit Type and Period Mock object
     */
  /*  @Bean
    public GetAccreditationUnitReportRequest aTypePeriodRequestMock()
    {
        PublicationType type = new PublicationType();
        type.setName("Accredited Journal");


        SimpleDateFormat tmp = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try
        {
            start = tmp.parse("2015-02-21");
            end = tmp.parse("2016-06-17");
        } catch (ParseException e1)
        {
            e1.printStackTrace();
        }
        Period p = new Period();
        p.setStartDate(start);
        p.setEndDate(end);

        return new GetAccreditationUnitReportRequest(null, null, type, p);
    }
*/
    /**
     *
     * @return AccreditationUnit State,Type and Period Mock object
     */
  /*  @Bean
    public GetAccreditationUnitReportRequest aStateTypePeriodRequestMock()
    {
        PublicationType type = new PublicationType();
        type.setName("Accredited Journal");
        LifeCycleState state = new LifeCycleState();
        state.setLifeCycleState("Published");

        SimpleDateFormat tmp = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try
        {
            start = tmp.parse("2015-01-10");
            end = tmp.parse("2016-12-22");
        } catch (ParseException e1)
        {
            e1.printStackTrace();
        }
        Period p = new Period();
        p.setStartDate(start);
        p.setEndDate(end);

        return new GetAccreditationUnitReportRequest(null, state, type, p);
    }
    */
    
}
