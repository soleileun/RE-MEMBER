<template>
	<div id="makepjt" class="container-contact100">
		<div class="wrap-contact100">
			<br />
			<form class="contact100-form validate-form">
				<span class="contact100-form-title"> <h2>프로젝트 생성페이지</h2> </span
				><br /><br />
				<div
					class="wrap-input100 validate-input"
					data-validate="Name is required"
				>
					<span class="label-input100">Project Name</span><br />
					<input
						v-model="pjtname"
						class="input100"
						type="text"
						name="pjtname"
						placeholder="Enter your project name"
					/>
					<span class="focus-input100"></span>
				</div>

				<div class="wrap-input100 input100-select">
					<div>
						<span class="label-input100">Member </span>

						<select class="selection-2" name="service">
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
							<option>6</option>
						</select>
					</div>
					<span class="focus-input100"></span>
				</div>
				<span class="label-input100">Content</span>
				<div>
					<textarea
						class="input100"
						name="message"
						placeholder="Your Content here..."
					></textarea>
					<span class="focus-input100"></span>
				</div>

				<div class="container-contact100-form-btn">
					<div class="wrap-contact100-form-btn">
						<div class="contact100-form-bgbtn"></div>
						<button class="contact100-form-btn">
							<span>
								Submit
								<i class="fa fa-long-arrow-right m-l-7" aria-hidden="true"></i>
							</span>
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</template>

<script>
export default {};
</script>

<style></style>
