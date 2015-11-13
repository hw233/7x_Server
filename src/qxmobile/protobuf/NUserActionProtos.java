// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: nUserAction.proto

package qxmobile.protobuf;

public final class NUserActionProtos {
  private NUserActionProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface NUserActionOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required string account = 1;
    /**
     * <code>required string account = 1;</code>
     */
    boolean hasAccount();
    /**
     * <code>required string account = 1;</code>
     */
    java.lang.String getAccount();
    /**
     * <code>required string account = 1;</code>
     */
    com.google.protobuf.ByteString
        getAccountBytes();

    // required int32 actionType = 2;
    /**
     * <code>required int32 actionType = 2;</code>
     *
     * <pre>
     * 1 攻占 2 修补
     * </pre>
     */
    boolean hasActionType();
    /**
     * <code>required int32 actionType = 2;</code>
     *
     * <pre>
     * 1 攻占 2 修补
     * </pre>
     */
    int getActionType();

    // required int32 cityId = 3;
    /**
     * <code>required int32 cityId = 3;</code>
     *
     * <pre>
     * 城池ID
     * </pre>
     */
    boolean hasCityId();
    /**
     * <code>required int32 cityId = 3;</code>
     *
     * <pre>
     * 城池ID
     * </pre>
     */
    int getCityId();
  }
  /**
   * Protobuf type {@code qxmobile.protobuf.NUserAction}
   */
  public static final class NUserAction extends
      com.google.protobuf.GeneratedMessage
      implements NUserActionOrBuilder {
    // Use NUserAction.newBuilder() to construct.
    private NUserAction(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private NUserAction(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final NUserAction defaultInstance;
    public static NUserAction getDefaultInstance() {
      return defaultInstance;
    }

    public NUserAction getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private NUserAction(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              bitField0_ |= 0x00000001;
              account_ = input.readBytes();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              actionType_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              cityId_ = input.readInt32();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return qxmobile.protobuf.NUserActionProtos.internal_static_qxmobile_protobuf_NUserAction_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return qxmobile.protobuf.NUserActionProtos.internal_static_qxmobile_protobuf_NUserAction_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              qxmobile.protobuf.NUserActionProtos.NUserAction.class, qxmobile.protobuf.NUserActionProtos.NUserAction.Builder.class);
    }

    public static com.google.protobuf.Parser<NUserAction> PARSER =
        new com.google.protobuf.AbstractParser<NUserAction>() {
      public NUserAction parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new NUserAction(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<NUserAction> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required string account = 1;
    public static final int ACCOUNT_FIELD_NUMBER = 1;
    private java.lang.Object account_;
    /**
     * <code>required string account = 1;</code>
     */
    public boolean hasAccount() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string account = 1;</code>
     */
    public java.lang.String getAccount() {
      java.lang.Object ref = account_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          account_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string account = 1;</code>
     */
    public com.google.protobuf.ByteString
        getAccountBytes() {
      java.lang.Object ref = account_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        account_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // required int32 actionType = 2;
    public static final int ACTIONTYPE_FIELD_NUMBER = 2;
    private int actionType_;
    /**
     * <code>required int32 actionType = 2;</code>
     *
     * <pre>
     * 1 攻占 2 修补
     * </pre>
     */
    public boolean hasActionType() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 actionType = 2;</code>
     *
     * <pre>
     * 1 攻占 2 修补
     * </pre>
     */
    public int getActionType() {
      return actionType_;
    }

    // required int32 cityId = 3;
    public static final int CITYID_FIELD_NUMBER = 3;
    private int cityId_;
    /**
     * <code>required int32 cityId = 3;</code>
     *
     * <pre>
     * 城池ID
     * </pre>
     */
    public boolean hasCityId() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required int32 cityId = 3;</code>
     *
     * <pre>
     * 城池ID
     * </pre>
     */
    public int getCityId() {
      return cityId_;
    }

    private void initFields() {
      account_ = "";
      actionType_ = 0;
      cityId_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasAccount()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasActionType()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasCityId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, getAccountBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, actionType_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, cityId_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, getAccountBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, actionType_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, cityId_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static qxmobile.protobuf.NUserActionProtos.NUserAction parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static qxmobile.protobuf.NUserActionProtos.NUserAction parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static qxmobile.protobuf.NUserActionProtos.NUserAction parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static qxmobile.protobuf.NUserActionProtos.NUserAction parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static qxmobile.protobuf.NUserActionProtos.NUserAction parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static qxmobile.protobuf.NUserActionProtos.NUserAction parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static qxmobile.protobuf.NUserActionProtos.NUserAction parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static qxmobile.protobuf.NUserActionProtos.NUserAction parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static qxmobile.protobuf.NUserActionProtos.NUserAction parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static qxmobile.protobuf.NUserActionProtos.NUserAction parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(qxmobile.protobuf.NUserActionProtos.NUserAction prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code qxmobile.protobuf.NUserAction}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements qxmobile.protobuf.NUserActionProtos.NUserActionOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return qxmobile.protobuf.NUserActionProtos.internal_static_qxmobile_protobuf_NUserAction_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return qxmobile.protobuf.NUserActionProtos.internal_static_qxmobile_protobuf_NUserAction_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                qxmobile.protobuf.NUserActionProtos.NUserAction.class, qxmobile.protobuf.NUserActionProtos.NUserAction.Builder.class);
      }

      // Construct using qxmobile.protobuf.NUserActionProtos.NUserAction.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        account_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        actionType_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        cityId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return qxmobile.protobuf.NUserActionProtos.internal_static_qxmobile_protobuf_NUserAction_descriptor;
      }

      public qxmobile.protobuf.NUserActionProtos.NUserAction getDefaultInstanceForType() {
        return qxmobile.protobuf.NUserActionProtos.NUserAction.getDefaultInstance();
      }

      public qxmobile.protobuf.NUserActionProtos.NUserAction build() {
        qxmobile.protobuf.NUserActionProtos.NUserAction result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public qxmobile.protobuf.NUserActionProtos.NUserAction buildPartial() {
        qxmobile.protobuf.NUserActionProtos.NUserAction result = new qxmobile.protobuf.NUserActionProtos.NUserAction(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.account_ = account_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.actionType_ = actionType_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.cityId_ = cityId_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof qxmobile.protobuf.NUserActionProtos.NUserAction) {
          return mergeFrom((qxmobile.protobuf.NUserActionProtos.NUserAction)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(qxmobile.protobuf.NUserActionProtos.NUserAction other) {
        if (other == qxmobile.protobuf.NUserActionProtos.NUserAction.getDefaultInstance()) return this;
        if (other.hasAccount()) {
          bitField0_ |= 0x00000001;
          account_ = other.account_;
          onChanged();
        }
        if (other.hasActionType()) {
          setActionType(other.getActionType());
        }
        if (other.hasCityId()) {
          setCityId(other.getCityId());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasAccount()) {
          
          return false;
        }
        if (!hasActionType()) {
          
          return false;
        }
        if (!hasCityId()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        qxmobile.protobuf.NUserActionProtos.NUserAction parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (qxmobile.protobuf.NUserActionProtos.NUserAction) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required string account = 1;
      private java.lang.Object account_ = "";
      /**
       * <code>required string account = 1;</code>
       */
      public boolean hasAccount() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required string account = 1;</code>
       */
      public java.lang.String getAccount() {
        java.lang.Object ref = account_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          account_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string account = 1;</code>
       */
      public com.google.protobuf.ByteString
          getAccountBytes() {
        java.lang.Object ref = account_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          account_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string account = 1;</code>
       */
      public Builder setAccount(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        account_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string account = 1;</code>
       */
      public Builder clearAccount() {
        bitField0_ = (bitField0_ & ~0x00000001);
        account_ = getDefaultInstance().getAccount();
        onChanged();
        return this;
      }
      /**
       * <code>required string account = 1;</code>
       */
      public Builder setAccountBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        account_ = value;
        onChanged();
        return this;
      }

      // required int32 actionType = 2;
      private int actionType_ ;
      /**
       * <code>required int32 actionType = 2;</code>
       *
       * <pre>
       * 1 攻占 2 修补
       * </pre>
       */
      public boolean hasActionType() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int32 actionType = 2;</code>
       *
       * <pre>
       * 1 攻占 2 修补
       * </pre>
       */
      public int getActionType() {
        return actionType_;
      }
      /**
       * <code>required int32 actionType = 2;</code>
       *
       * <pre>
       * 1 攻占 2 修补
       * </pre>
       */
      public Builder setActionType(int value) {
        bitField0_ |= 0x00000002;
        actionType_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 actionType = 2;</code>
       *
       * <pre>
       * 1 攻占 2 修补
       * </pre>
       */
      public Builder clearActionType() {
        bitField0_ = (bitField0_ & ~0x00000002);
        actionType_ = 0;
        onChanged();
        return this;
      }

      // required int32 cityId = 3;
      private int cityId_ ;
      /**
       * <code>required int32 cityId = 3;</code>
       *
       * <pre>
       * 城池ID
       * </pre>
       */
      public boolean hasCityId() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required int32 cityId = 3;</code>
       *
       * <pre>
       * 城池ID
       * </pre>
       */
      public int getCityId() {
        return cityId_;
      }
      /**
       * <code>required int32 cityId = 3;</code>
       *
       * <pre>
       * 城池ID
       * </pre>
       */
      public Builder setCityId(int value) {
        bitField0_ |= 0x00000004;
        cityId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 cityId = 3;</code>
       *
       * <pre>
       * 城池ID
       * </pre>
       */
      public Builder clearCityId() {
        bitField0_ = (bitField0_ & ~0x00000004);
        cityId_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:qxmobile.protobuf.NUserAction)
    }

    static {
      defaultInstance = new NUserAction(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:qxmobile.protobuf.NUserAction)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_qxmobile_protobuf_NUserAction_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_qxmobile_protobuf_NUserAction_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021nUserAction.proto\022\021qxmobile.protobuf\"B" +
      "\n\013NUserAction\022\017\n\007account\030\001 \002(\t\022\022\n\naction" +
      "Type\030\002 \002(\005\022\016\n\006cityId\030\003 \002(\005B\023B\021NUserActio" +
      "nProtos"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_qxmobile_protobuf_NUserAction_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_qxmobile_protobuf_NUserAction_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_qxmobile_protobuf_NUserAction_descriptor,
              new java.lang.String[] { "Account", "ActionType", "CityId", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}