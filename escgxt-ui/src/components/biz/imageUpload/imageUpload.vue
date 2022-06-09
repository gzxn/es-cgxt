<template>
	<el-upload class="avatar-uploader" :action="action" :show-file-list="false" :on-success="handleAvatarSuccess"
		:before-upload="beforeAvatarUpload" :headers="headers" :data="data" :auto-upload="!isBase64"
		:on-change="changeImg">
		<img v-if="imageUrl" :src="imageUrl" class="avatar">
		<i v-else class="el-icon-plus avatar-uploader-icon"></i>
	</el-upload>
</template>

<script>
	export default {
		name: 'imageUpload',
		//上面是父组件可传入参数
		data() {
			return {
				imageUrl: '',
				action: this.$constant.serverUrl + "/components/uploadComponent/imageUpload",
				headers: {
					jwttoken: this.$caches.token.get()
				},
				data: {
					statisPath: this.statisPath
				},
			};
		},
		model: {
			prop: 'value',
			event: 'change'
		},
		methods: {
			handleAvatarSuccess(res, file) {
				if (res.code == 1000) {
					this.imageUrl = URL.createObjectURL(file.raw);
					//默认，选择图片使用流上传
					// this.imageUrl = this.$constant.serverUrl + res.data;
					this.$emit("change", res.data);
				}
			},
			beforeAvatarUpload(file) {
				const isJPG = file.type === 'image/jpeg' || file.type === 'image/png' || file.type === 'image/jpg';
				const isLt2M = file.size / 1024 < this.limiter;
				if (!isJPG) {
					this.$utils.msg.warning('上传头像图片只能是 JPEG/JPG/PNG 格式!');
				}
				if (!isLt2M) {
					this.$utils.msg.warning('上传头像图片大小不能超过 ' + this.limiter + 'KB!');
				}
				return isJPG && isLt2M;
			},
			//选择图片后
			changeImg(file, fileList) {
				if (this.isBase64) {
					file = file.raw;
					const isJPG = file.type === 'image/jpeg' || file.type === 'image/png' || file.type === 'image/jpg';
					const isLt2M = file.size / 1024 < this.limiter;
					if (!isJPG) {
						this.$utils.msg.warning('上传头像图片只能是 JPEG/JPG/PNG 格式!');
						return;
					}
					if (!isLt2M) {
						this.$utils.msg.warning('上传头像图片大小不能超过 ' + this.limiter + 'KB!');
						return;
					}
					this.imageUrl = URL.createObjectURL(file);
					this.getBase64(file).then(res => {
						this.$emit("change", res);
					});;
				}
			},
			getBase64(file) {
				return new Promise(function(resolve, reject) {
					let reader = new FileReader();
					let imgResult = '';
					reader.readAsDataURL(file);
					reader.onload = function() {
						imgResult = reader.result;
					};
					reader.onerror = function(error) {
						reject(error);
					};
					reader.onloadend = function() {
						resolve(imgResult);
					};
				});
			},
		},
		props: {
			value: {
				type: String,
				required: false,
				default: ""
			},
			/**
			 * 是否base64格式
			 */
			isBase64: {
				type: Boolean,
				required: false,
				default: true
			},
			/**
			 * 保存目录
			 */
			statisPath: {
				type: String,
				required: false,
				default: ""
			},
			/**
			 * 上传大小限制，单位KB
			 */
			limiter: {
				type: Number,
				required: false,
				default: 64
			},
		},
		watch: {
			value: {
				handler: function(val) {
					this.imageUrl = "";
					if (val) {
						if (this.isBase64) {
							this.imageUrl = val
						} else {
							this.imageUrl = this.$constant.serverUrl + val
						}
					}
				},
				immediate: true,
				deep: true
			}
		}
	};
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
