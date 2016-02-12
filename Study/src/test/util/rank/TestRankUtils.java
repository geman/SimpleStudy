package test.util.rank;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import com.util.rank.RankUtils;

public class TestRankUtils {
	
	@Test
	public void testFindRank() {
		List<String> list = new ArrayList<>() ;
		list.add("a") ;
		list.add("b") ;
		list.add("c") ;
		list.add("d") ;
		list.add("a") ;
		
		int pickNum = 3;
		//��������List�Ƿ��С��ȷ
		List<List<String>> rsList = RankUtils.findRank(list, pickNum) ;
		int mustSize = 1 ;
		for (int i = 0; i < pickNum; i++) {
			mustSize *= (list.size()-i);
		}
		System.out.println("findRank size :"+rsList.size());
		assertTrue(rsList.size() == mustSize) ;
		//����Ƿ����ظ�������
		boolean isRepeat = false ;
		Set<String> set = new HashSet<>() ;
		
		for (int i = 0; i < rsList.size(); i++) {
			StringBuffer sb = new StringBuffer() ;
			List<String> rses = rsList.get(i) ;
			for (int j = 0; j < rses.size(); j++) {
				sb.append(rses.get(j)) ;
			}
			if(set.contains(sb.toString())){
				isRepeat = true ;
				System.out.println("sb is  : "+sb.toString());
				System.out.println("set is : "+set.toString());
			}else{
				set.add(sb.toString()) ;
			}
		}
		System.out.println("rs set:"+set);
		assertTrue(!isRepeat);
		
	}
	
	@Ignore
	@Test
	public void testFindGroup() {
		List<String> list = new ArrayList<>() ;
		list.add("a") ;
		list.add("b") ;
		list.add("c") ;
		list.add("d") ;
		
		int pickNum = 4;
		//��������List�Ƿ��С��ȷ
		List<List<String>> rsList = RankUtils.findGroup(list, pickNum) ;
		
		int mustSize = 1 ;
		int digitSize = 1 ;
		for (int i = 0; i < pickNum; i++) {
			mustSize *= (list.size()-i);
		}
		
		for (int i = 1; i <= pickNum; i++) {
			digitSize *= i ;
		}
		
		System.out.println("group size :"+rsList.size());
		assertTrue(rsList.size() == mustSize/digitSize) ;
		//����Ƿ����ظ�������
		boolean isRepeat = false ;
		Set<String> set = new HashSet<>() ;
		
		for (int i = 0; i < rsList.size(); i++) {
			StringBuffer sb = new StringBuffer() ;
			List<String> rses = rsList.get(i) ;
			for (int j = 0; j < rses.size(); j++) {
				sb.append(rses.get(j)) ;
			}
			if(set.contains(sb.toString())){
				isRepeat = true ;
				System.out.println("sb is  : "+sb.toString());
				System.out.println("set is : "+set.toString());
			}else{
				set.add(sb.toString()) ;
			}
		}
		System.out.println("rs set:"+set);
		assertTrue(!isRepeat);
	}
	@Ignore
	@Test
	public void testFindAllRank() {
		List<String> list = new ArrayList<>() ;
		list.add("a") ;
		list.add("b") ;
		list.add("c") ;
		list.add("d") ;
		
		//��������List�Ƿ��С��ȷ
		List<List<String>> rsList = RankUtils.findAllRank(list) ;
		
		int mustSize = 1 ;
		for (int i = 1; i <= list.size(); i++) {
			mustSize *=i ;
		}
		
		assertTrue(rsList.size() == mustSize);
		//����Ƿ����ظ�������
		boolean isRepeat = false ;
		Set<String> set = new HashSet<>() ;
		
		for (int i = 0; i < rsList.size(); i++) {
			StringBuffer sb = new StringBuffer() ;
			List<String> rses = rsList.get(i) ;
			for (int j = 0; j < rses.size(); j++) {
				sb.append(rses.get(j)) ;
			}
			if(set.contains(sb.toString())){
				isRepeat = true ;
				System.out.println("sb is  : "+sb.toString());
				System.out.println("set is : "+set.toString());
			}else{
				set.add(sb.toString()) ;
			}
		}
		System.out.println("rs set:"+set);
		assertTrue(!isRepeat);
	}

}
