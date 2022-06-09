<template>
	<cascader ref="cascader" :clearable="clearable" :show-all-levels="showAllLevels" :collapse-tags="collapseTags"
		v-model="selectValue" :limitLevel="limitLevel" :filterable="filterable" dataMode="standard" :options="options"
		:disabled="disabled" :propOptions="propOptions" @expand-change="expandChange" @blur="blur" @focus="focus"
		:placeholder="placeholder" @visible-change="visibleChange" @remove-tag="removeTag">
	</cascader>
</template>

<script>
	import cascader from '@/components/cascader/cascader.vue';
	export default {
		name: 'araeCascader',
		components: {
			cascader,
		},
		data() {
			return {
				options: [],
				selectValue: []
			}
		},
		model: {
			prop: 'value',
			event: 'change'
		},
		props: {
			// 是否禁用
			disabled: {
				type: Boolean,
				default () {
					return false;
				}
			},
			placeholder: {
				type: String,
				required: false,
				default: "请选择"
			},
			//数据类型  simple-简单数据、standard-标准
			dataMode: {
				type: String,
				default () {
					return "simple";
				}
			},
			// 是否可清空
			clearable: {
				type: Boolean,
				default () {
					return true;
				}
			},
			// 是否显示完整的路径
			showAllLevels: {
				type: Boolean,
				default () {
					return true;
				}
			},
			// 是否折叠(开启多选模式后可设置)
			collapseTags: {
				type: Boolean,
				default () {
					return false;
				}
			},
			//是否开启搜索功能
			filterable: {
				type: Boolean,
				default () {
					return true;
				}
			},
			//返回的层级
			limitLevel: {
				type: Number,
				default () {
					return 3;
				}
			},
			// 回显选中的值
			value: {
				type: Array,
				default () {
					return [];
				}
			},
			//返回选中节点的所有信息
			getAllInfo: {
				type: Boolean,
				default () {
					return false;
				}
			},
			// 属性选项
			propOptions: {
				type: Object,
				default () {
					return {
						multiple: false, //是否多选
						checkStrictly: false, //是否选择任意一级选项
						value: "id", //指定选项的值为选项对象的某个属性值
					};
				}
			}
		},
		methods: {
			init() {
				this.list();
			},
			list() {
				this.options = this.$caches.areasStandard.get();
			},
			// 当展开节点发生变化时触发 回调：各父级选项值组成的数组
			expandChange(val) {
				this.$emit("expand-change", val)
			},
			// 当失去焦点时触发 回调：event
			blur(event) {
				this.$emit("blur", event)
			},
			// 当获得焦点时触发 回调：event
			focus(event) {
				this.$emit("focus", event)
			},
			// 下拉框出现/隐藏时触发 回调：出现则为 true，隐藏则为 false
			visibleChange(val) {
				this.$emit("visible-change", val)
			},
			// 在多选模式下，移除Tag时触发 回调：移除的Tag对应的节点的值
			removeTag(val) {
				this.$emit("remove-tag", val)
			},
			getCheckedObj() {
				return this.$refs.cascader.getCheckedObj();
			}
		},
		watch: {
			value: {
				handler: function(val) {
					this.selectValue = val;
				},
				immediate: true,
				deep: true
			},
			selectValue: {
				handler: function(val) {
					this.$emit("change", this.selectValue);
				},
				immediate: true,
				deep: true
			},
		},
		created() {
			this.$nextTick(() => {
				this.init();
			})
		}
	}
</script>

<style>
</style>
