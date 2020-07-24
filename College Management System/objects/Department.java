package objects;

public class Department {
		
		long deptCode;
		String deptName;
		
		public Department(long deptCode, String deptName) {
			this.deptCode = deptCode;
			this.deptName = deptName;
		}

		public long getDeptCode() {
			return deptCode;
		}

		public void setDeptCode(long deptCode) {
			this.deptCode = deptCode;
		}

		public String getDeptName() {
			return deptName;
		}

		public void setDeptName(String deptName) {
			this.deptName = deptName;
		}
		
		
}
